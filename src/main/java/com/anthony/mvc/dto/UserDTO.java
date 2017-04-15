package com.anthony.mvc.dto;

/**
 * Created by chend on 2017/4/15.
 */
public class UserDTO {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
