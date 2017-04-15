package com.anthony.mvc.service;

import com.anthony.mvc.dao.UserDAO;
import com.anthony.mvc.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chend on 2017/4/15.
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public List<UserDTO> getUser()
    {
        return userDAO.getUser();
    }
}
