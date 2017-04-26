package com.anthony.user.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
@Mapper
public interface UserDAO {
    boolean isUserPasswordRight(HashMap paraMap);
}
