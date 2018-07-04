package com.cevs.rentall.controllers;

import com.cevs.rentall.dto.BuyerDto;
import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    IUserService userService;

    @RequestMapping(path = {"/registration/buyer"}, method = RequestMethod.POST)
    public String registrationBuyer(@Valid BuyerDto buyerDto, BindingResult bindingResult,
                                    ModelMap model, RedirectAttributes ra){
        if(!bindingResult.hasErrors()){
            boolean registrationSuccess = userService.registerBuyer(buyerDto);
            if(registrationSuccess){
                model.addAttribute("message","Sucessful registration");
                //Set model as flash attribute and sent it to login controlelr
                ra.addFlashAttribute("model",model);
                return "redirect:/login";
            }
        }
        model.addAttribute("message", "Registration failed");
        return "registration-buyer";
    }

    //This method is needed for returning validation errors
    @RequestMapping(path = {"/registration/buyer"}, method = RequestMethod.GET)
    public String registrationBuyer(BuyerDto buyerDto){
        return "registration-buyer";
    }

    @RequestMapping(path = {"/registration/renter"}, method = RequestMethod.POST)
    public String  registrationRenter(@Valid RenterDto renterDto, BindingResult bindingResult,
                                      ModelMap model, RedirectAttributes ra){
        if(!bindingResult.hasErrors()){
            boolean registrationSuccess = userService.registerRenter(renterDto);
            if(registrationSuccess){
                model.addAttribute("message","Sucessful registration");
                //Set model as flash attribute and sent it to login controlelr
                ra.addFlashAttribute("model",model);
                return "redirect:/login";
            }
        }
        model.addAttribute("message", "Registration failed");
        return "registration-renter";
    }

    //This method is needed for returning validation errors
    @RequestMapping(path = {"/registration/renter"}, method = RequestMethod.GET)
    public String registrationRenter(RenterDto renterDto){
        return "registration-renter";
    }
}
