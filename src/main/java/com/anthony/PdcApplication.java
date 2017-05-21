package com.anthony;

import com.anthony.config.SystemInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by chend on 2017/3/26.
 */

@SpringBootApplication
public class PdcApplication {
    public static void main(String[] args) throws Exception {
        SystemInitializer.getInstance().init();
        //通过SpringApplication的run()方法启动应用，无需额外的配置其他的文件
        SpringApplication.run(PdcApplication.class, args);
    }

}
//http://ourjs.com/detail/529ca5950cb6498814000005

//npm config set registry https://registry.npmjs.org/

