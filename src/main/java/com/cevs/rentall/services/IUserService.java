package com.cevs.rentall.services;

import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface IUserService {
    boolean registerBuyer(BuyerDto buyerDto);
    boolean registerRenter(RenterDto renterDto);
}
