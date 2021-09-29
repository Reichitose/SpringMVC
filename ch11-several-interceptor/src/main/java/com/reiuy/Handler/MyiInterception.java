package com.reiuy.Handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyiInterception implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //给time赋值当前的系统时间

        System.out.println("这里是MyiInterception1的preHandle");
        //在方法内部有逻辑,根据结果返回true和false
        //拦截请求并给浏览器一个返回结果
        //request.getRequestDispatcher("/tips.jsp").forward(request,response);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("这里是MyiInterception1的postHandle");
        //对doSome的执行结果进行一个二次修正
        //注意这个修正是增加而不是修改,原来被加入的数据并不会消失
        //但是对于视图来讲,因为指定的view是唯一的,所以这里是替换而不是增加
        if(modelAndView != null){
            //这里是增加了一个mydate
            modelAndView.addObject("mydate",new Date());
            //将其视图修改为另一个页面
            modelAndView.setViewName("other");
        }
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这里是MyiInterception1的afterCompletion");

    }
}
