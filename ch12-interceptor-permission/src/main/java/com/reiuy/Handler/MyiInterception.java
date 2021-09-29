package com.reiuy.Handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyiInterception implements HandlerInterceptor {


    //验证登录的用户信息,正确放行,错误截断
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("这里是MyiInterception1的preHandle");
        //从session中获取name
        String loginname = "";
        Object attr = request.getSession().getAttribute("name");
        if(attr != null){
            loginname = (String) attr;
        }

        //判断登录的账号是否符合要求
        if(!"nb".equals(loginname)){
            //不能访问
            //提示错误
            request.getRequestDispatcher("/tips.jsp").forward(request,response);
            return false;
        }

        //正确的nb登录
        return true;
    }

}
