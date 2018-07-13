package com.cevs.rentall.services;

import com.cevs.rentall.dao.IUserDao;
import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public boolean registerBuyer(BuyerDto buyerDto)  {
        try {
            Buyer buyer = new Buyer(buyerDto);
            if(!checkIfUserAlreadyExists(buyer)){
                userDao.insertBuyer(buyer);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerRenter(RenterDto renterDto) {
        try{
            Renter renter = new Renter(renterDto);
            if(!checkIfUserAlreadyExists(renter)){
                userDao.insertRenter(renter);
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProfile(RenterDto renterDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            Renter renter = new Renter(renterDto);
            renter.setId(user.getId());
            renter.setImage(convertToBase64(renterDto.getFile()));
            userDao.updateRenter(renter);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProfile(BuyerDto buyerDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            Buyer buyer = new Buyer(buyerDto);
            buyer.setId(user.getId());
            buyer.setImage(convertToBase64(buyerDto.getFile()));
            userDao.updateBuyer(buyer);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Renter getRenterProfileInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return userDao.getRenterProfileInfo(renter.getId());
    }

    @Override
    public RenterInfo getRenterInfo(int id) {
        return userDao.getRenterInfo(id);
    }

    @Override
    public Buyer getBuyerProfileInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = (User) auth.getPrincipal();
        return userDao.getBuyerProfileInfo(buyer.getId());
    }

    @Override
    public List<User> getUsers(String search, String userType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if(user.getUserType().equals("Administrator")){
            String table = "";
            if(userType.equals("Renters")){
                table = "renters";
            }else if (userType.equals("Buyers")){
                table = "buyers";
            }else{
                table = "users";
            }
            return userDao.getUsers(search, table);
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            if(user.getUserType().equals("Administrator")){
                userDao.deleteUser(userId);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean lockUser(int id, boolean locked) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            if(user.getUserType().equals("Administrator")){
                userDao.lockUser(id, locked);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkIfUserAlreadyExists(User newUser){
        User user = userDao.findUserByEmail(newUser.getEmail());
        if(user != null){
            return true;
        }
        return false;
    }

    private MultipartFile convertToMultipart(byte[] imgBytes) {
        try {
            Path path = Files.createTempFile("image",".png");
            Files.write(path, imgBytes);
            MultipartFile multipartFile = new MockMultipartFile("image.png",
                    new FileInputStream(path.toFile()));
            path.toFile().deleteOnExit();
            return multipartFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String convertToBase64(MultipartFile image) {
        try {
            byte[] encoded = Base64.getEncoder().encode(image.getBytes());
            return (new String(encoded));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
