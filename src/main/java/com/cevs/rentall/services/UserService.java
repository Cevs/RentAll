package com.cevs.rentall.services;

import com.cevs.rentall.dao.IUserDao;
import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Location;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;

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
            renter.setImage(renterDto.getFile());
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
            buyer.setImage(buyerDto.getFile());
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
    public Buyer getBuyerProfileInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = (User) auth.getPrincipal();
        return userDao.getBuyerProfileInfo(buyer.getId());
    }

    public boolean checkIfUserAlreadyExists(User newUser){
        User user = userDao.findUserByEmail(newUser.getEmail());
        if(user != null){
            return true;
        }
        return false;
    }
}
