package com.anthony.config.filter;

import com.anthony.common.UserManager;
import com.anthony.user.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by CHENDONG239 on 2017-04-25.
 */
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        System.out.println("token:"+token);
        UserDTO userDTO = UserManager.getInstance().getUserByToken(token);

        if (null == userDTO) {
            System.out.println("新用户登录,跳转");
            chain.doFilter(request, response);
        }
        else {
            System.out.println("用户已登录:" + userDTO);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
