package com.sccdrs.work.controller;

import com.alibaba.fastjson.JSON;
import com.sccdrs.work.aop.Operation;
import com.sccdrs.work.service.UserService;
import com.sccdrs.work.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(value = "查询用户信息记录")
    @RequestMapping("getUser/{id}")
    public String getUser(@PathVariable int id){
        return userService.getUserById(id).toString();
    }
    @Operation(value = "修改用户状态记录")
    @PostMapping("updateUser")
    public JSON updateUser(int id, int status){
        int num = userService.updateUser(id,status);
        if(num !=0){
            return ResponseUtil.success("修改成功");
        }else{
            return ResponseUtil.fail("修改失败");
        }
    }


}
