package com.anthony.hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

//表明这是一个 Controller
//@Controller

//RestController是一种Rest风格的Controller，可以直接返回对象而不返回视图，返回的对象可以使JSON，XML等
//@RestController

//使用自动配置，主动添加并解析bean，配置文件等信息
//@EnableAutoConfiguration

public class SpringController {

    @Autowired
    TestDAO testDAO;

    //设置访问的url
    @RequestMapping("/")
    //表示返回JSON格式的结果，如果前面使用的是@RestController可以不用写
//    @ResponseBody
    String home() {
        return testDAO.getUser().get(0).toString();//返回结果为字符串
    }

    @RequestMapping("/{name}/{age}")
    Dto home1(Dto t) {
        return t;//返回结果为字符串
    }

}

class Dto
{
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