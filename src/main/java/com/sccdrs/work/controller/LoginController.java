package com.sccdrs.work.controller;

import com.sccdrs.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
