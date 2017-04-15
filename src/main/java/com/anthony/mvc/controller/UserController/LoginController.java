package com.anthony.mvc.controller.UserController;

import com.anthony.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chend on 2017/4/15.
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("/aa")
    public String userLogin()
    {
        return userService.getUser().get(0).toString();
    }

//    @RequestMapping("/")
//    public String index()
//    {
//        return "forward:index.html";
//    }
}
