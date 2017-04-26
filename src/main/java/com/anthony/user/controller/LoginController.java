package com.anthony.user.controller;

import com.anthony.user.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@RestController
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping("/login/{userName}/{password}")
    public void login(@PathVariable String userName, @PathVariable String password)
    {
        HashMap<String,String> paraMap=new HashMap<>();
        paraMap.put("userName",userName);
        paraMap.put("password",password);
        userService.Login(paraMap);
        System.out.println(userName);
        System.out.println(password);
    }

}
