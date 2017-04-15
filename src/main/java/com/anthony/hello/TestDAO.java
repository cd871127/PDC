package com.anthony.hello;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by chend on 2017/4/15.
 */
@Mapper
public interface TestDAO {
    public List<Dto> getUser();
}
