package com.sccdrs.work.controller;

import com.sccdrs.work.service.UserService;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wcy
 * @date 2019/7/26 16:34
 * @Description:
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public ModelAndView login(){
        ModelAndView m = new ModelAndView();
        m.setViewName("index");
        return m;
    }

    @RequestMapping("/getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }
}
