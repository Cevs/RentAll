package com.cevs.rentall.dto.com.cevs.rentall.dto.validation;

import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if(obj instanceof RenterDto){
            RenterDto renterDto = (RenterDto) obj;
            return renterDto.getPassword().equals(renterDto.getRePassword());
        }else if(obj instanceof BuyerDto){
            BuyerDto buyerDto = (BuyerDto) obj;
            return  buyerDto.getPassword().equals(buyerDto.getRePassword());
        }else{
            return false;
        }
    }
}
