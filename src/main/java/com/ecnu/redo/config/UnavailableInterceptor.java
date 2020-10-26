package com.ecnu.redo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class UnavailableInterceptor  implements HandlerInterceptor  {
    @Override
//    @Bean
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println( request.getRequestURI());
        return true;
    }

}
