package com.anthony.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@RestController
public class LogoutController {
    @RequestMapping("/login/{userName}")
    public void login(@PathVariable String userName)
    {
        System.out.println(userName);
    }
}
