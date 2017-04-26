package com.anthony.user.service;

import com.anthony.user.dao.UserDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@Service
public class UserService {

    @Resource
    UserDAO userDAO;

    public String Login(HashMap paraMap) {
        boolean flag=userDAO.isUserPasswordRight(paraMap);
        System.out.println(flag);
        return null;
    }
}
