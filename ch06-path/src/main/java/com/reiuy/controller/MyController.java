package com.reiuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
//可以将这些功能全部放入test模块.例如user模块,来表明这个Controller完成的功能属于哪个部分
public class MyController {


    //我们指定some.do使用get请求方式
    @RequestMapping(value = "/test/some.do",method = RequestMethod.GET)

    public ModelAndView doSome(){
        //在dosome中处理some.do的请求
        ModelAndView mv = new ModelAndView();

        //添加数据,框架在请求的最后部分,会把数据放入request作用域
        //相当于执行了request.setAttribute
        mv.addObject("msg","hello springmvc");
        mv.addObject("func","执行了dosome方法");


        mv.setViewName("show");



        //返回mv
        return mv;
    }

}
