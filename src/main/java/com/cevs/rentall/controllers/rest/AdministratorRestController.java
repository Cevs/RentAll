package com.cevs.rentall.controllers.rest;

import com.cevs.rentall.models.User;
import com.cevs.rentall.services.IUserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class AdministratorRestController {

    @Autowired
    IUserService userService;

    @RequestMapping("/administrator/users/get")
    public List<User> getUsers(@RequestParam("search") String search,
                               @RequestParam("type") String type){
        return userService.getUsers(search, type);
    }

    @DeleteMapping("/administrator/users/{id}")
    public String  deleteUser(@PathVariable int id){
        if(userService.deleteUser(id)){
            return Boolean.TRUE.toString();
        }
        return  Boolean.FALSE.toString();
    }

    @PutMapping("/administrator/users/lock/{id}")
    public String lockUser(@PathVariable int id, @RequestParam("locked") boolean locked){
        if(userService.lockUser(id, locked)){
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();
    }
}
