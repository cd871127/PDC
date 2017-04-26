package com.anthony.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by CHENDONG239 on 2017-04-26.
 */
public class SystemInit {
    private static SystemInit instance = new SystemInit();
    private SystemInit (){}
    public static SystemInit getInstance() {
        return instance;
    }

    public void init()
    {

    }

    private Properties loadParameter()
    {
        Properties properties = new Properties();
        try(FileInputStream in=new FileInputStream("a.properties")) {
//            in = new FileInputStream("a.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
