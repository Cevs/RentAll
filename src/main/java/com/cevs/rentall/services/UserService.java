package com.cevs.rentall.services;

import com.cevs.rentall.dao.UserDao;
import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class UserService implements IUserService {
    @Autowired
    UserDao userDao;

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

    public boolean checkIfUserAlreadyExists(User newUser){
        User user = userDao.findUserByEmail(newUser.getEmail());
        if(user != null){
            return true;
        }
        return false;
    }
}
