package com.anthony.user.controller;

import com.anthony.common.UserManager;
import com.anthony.user.dto.UserDTO;
import com.anthony.user.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@RestController
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping("/login/{userName}/{password}")
    public Object login(@PathVariable String userName, @PathVariable String password, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> paraMap = new HashMap<>();
        paraMap.put("userName", userName);
        paraMap.put("password", password);

        UserDTO userDTO = userService.userLogin(paraMap);
        if (null == userDTO) {
            return "登录失败";
        }
        System.out.println("新用户登录12:" + userDTO.getToken());
        System.out.println(UserManager.getInstance().getUserMap().size());
        return userDTO;
    }
}
