package com.anthony.user.service;

import com.anthony.common.UserManager;
import com.anthony.user.dao.UserDAO;
import com.anthony.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@Service
public class UserService {

    @Resource
    private UserDAO userDAO;

    public UserDTO userLogin(HashMap paraMap) {
        UserDTO userDTO = userDAO.getUserInfoByUserName(paraMap);
        if (null == userDTO) {
            //登录失败
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String originToken = userDTO.getUserName() + userDTO.getActiveTime();
            md.update(originToken.getBytes());
            userDTO.setToken(new BigInteger(1, md.digest()).toString(16));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        UserManager.getInstance().addUser(userDTO);
        return userDTO;
    }

}
