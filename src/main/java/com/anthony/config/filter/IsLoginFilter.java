package com.anthony.config.filter;

import com.anthony.common.UserManager;
import com.anthony.user.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CHENDONG239 on 2017-04-25.
 * 判断用户是否登录
 */
public class IsLoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        UserDTO userDTO = UserManager.getInstance().getUserByToken(token);
        chain.doFilter(request, response);
//        if (null == userDTO) {
//            ((HttpServletResponse)response).sendRedirect("testReact");
//        }
//        else {
//            System.out.println("用户已登录:" + userDTO);
//            chain.doFilter(request, response);
//        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
