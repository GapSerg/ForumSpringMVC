package org.example.config;

import org.example.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter({"/user/*","/admin/*"})
public class FilterCookie implements Filter {
    private static int countGlobal;
    private int countLocal;




    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      // Filter.super.init(filterConfig);
        System.out.println("////////-----------start---------------//////////////");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        countLocal++;
        countGlobal++;

        System.out.println("////////-----Local="+countLocal+"------Global="+countGlobal+"--------//////////////");
//        int flag=0;
        for (Cookie cook : cookies) {
            System.out.println(cook.getName()+"-------"+cook.getValue());


        }
//        if (flag==2){
             filterChain.doFilter(request, response);
//        }
//        else
//            request.getRequestDispatcher("/start").forward(request, response);

    }


    @Override
    public void destroy() {
     //   Filter.super.destroy();
        System.out.println("////////-----------finish---------------//////////////");
    }
}
