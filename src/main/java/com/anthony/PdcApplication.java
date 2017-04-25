package com.anthony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by chend on 2017/3/26.
 */

@ComponentScan
@EnableAutoConfiguration
public class PdcApplication {
        public static void main(String[] args) throws Exception {
            //通过SpringApplication的run()方法启动应用，无需额外的配置其他的文件
            SpringApplication.run(PdcApplication.class, args);
        }
    }
//http://ourjs.com/detail/529ca5950cb6498814000005

