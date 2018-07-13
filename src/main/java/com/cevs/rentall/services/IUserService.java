package com.cevs.rentall.services;

import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.models.RenterInfo;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface IUserService {
    boolean registerBuyer(BuyerDto buyerDto);
    boolean registerRenter(RenterDto renterDto);
    boolean updateProfile(RenterDto renterDto);
    boolean updateProfile(BuyerDto buyerDto);
    Renter getRenterProfileInfo();
    RenterInfo getRenterInfo(int id);
    Buyer getBuyerProfileInfo();
}
