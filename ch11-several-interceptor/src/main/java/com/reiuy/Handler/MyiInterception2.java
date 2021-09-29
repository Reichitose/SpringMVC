package com.reiuy.Handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyiInterception2 implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //给time赋值当前的系统时间

        System.out.println("这里是MyiInterception2的preHandle");
        //在方法内部有逻辑,根据结果返回true和false
        //拦截请求并给浏览器一个返回结果
        //request.getRequestDispatcher("/tips.jsp").forward(request,response);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("这里是MyiInterception2的postHandle");
        if(modelAndView != null){
            //这里是增加了一个mydate
            modelAndView.addObject("mydate",new Date());
            //将其视图修改为另一个页面
            modelAndView.setViewName("other");
        }
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这里是MyiInterception2的afterCompletion");

    }
}
