package com.anthony.user.controller;

import com.anthony.common.UserContainer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@RestController
public class LogoutController {
    @RequestMapping("/logout/{userName}")
    public void logout(@PathVariable String userName)
    {
//        UserContainer.getInstance().getUserMap().remove(userName);
    }
}
