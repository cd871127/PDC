package com.anthony.user.dao;

import com.anthony.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@Mapper
@Repository
public interface UserDAO {
    UserDTO getUserInfoByUserName(HashMap paraMap);
}
