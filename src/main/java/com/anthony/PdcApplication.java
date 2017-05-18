package com.anthony;

import com.anthony.config.SystemInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chend on 2017/3/26.
 */

@SpringBootApplication
public class PdcApplication {
    public static void main(String[] args) throws Exception {
        Properties p=System.getProperties();
        Iterator it=p.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key +":"+value);
        }
        SystemInitializer.getInstance().init();
        //通过SpringApplication的run()方法启动应用，无需额外的配置其他的文件
        SpringApplication.run(PdcApplication.class, args);
    }

}
//http://ourjs.com/detail/529ca5950cb6498814000005

