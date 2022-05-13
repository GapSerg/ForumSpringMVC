package org.example.config;

import org.example.dao.UserDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter({"/user/*", "/admin/*"})
public class FilterCookie implements Filter {
private UserDAO userDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();


        if (session.getAttribute("user") != null) {
            filterChain.doFilter(request, response);

        } else {
           httpResponce.sendRedirect("/start");
        }


    }


    @Override
    public void destroy() {

    }
}
