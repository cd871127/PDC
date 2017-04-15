package com.anthony.mvc.dao;

import com.anthony.mvc.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by chend on 2017/4/15.
 */
@Mapper
public interface UserDAO {
    List<UserDTO> getUser();
}
