package com.anthony.config.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by CHENDONG239 on 2017-04-27.
 */
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String userName=request.getParameter("userName");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
