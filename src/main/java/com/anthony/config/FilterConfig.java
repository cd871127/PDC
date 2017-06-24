package com.anthony.config;

import com.anthony.config.filter.IsLoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * Created by CHENDONG239 on 2017-04-25.
 */
@Configuration
public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean greetingFilterRegistrationBean() {
////        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
////        registrationBean.setName("isLoginFilter");
////        IsLoginFilter isLoginFilter = new IsLoginFilter();
////        registrationBean.setFilter(isLoginFilter);
////        registrationBean.setOrder(1);
////        ArrayList<String> urlList = new ArrayList<>();
////        urlList.add("/login/*");
////        registrationBean.setUrlPatterns(urlList);
//        return registrationBean;
//    }
}
