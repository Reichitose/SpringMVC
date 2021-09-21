package com.reiuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @RequestMapping
 *  value:表示所有请求地址的公共部分,例如本段中的/test
 *  此时可以放在类的上方
 *  这个公共部分被称为模块名称
 *  这和方法上的使用不冲突
 */



@Controller
@RequestMapping("/test")
//可以将这些功能全部放入test模块.例如user模块,来表明这个Controller完成的功能属于哪个部分
public class MyController {


    /***
     * @RequestMapping  请求映射
     *              属性  method ,表示请求的方式.它的值是RequestMethod类的枚举值
     *              例如表示get请求方式,RequestMethod.GET
     *              同理,post请求方式是RequestMethod.POST
     *
     *              如果不指定method属性,那就不做限制,使用post和get都是可以的
     *              定义了get,使用post或者反之都会产生405 方法不支持错误
     */

    //我们指定some.do使用get请求方式
    @RequestMapping(value = "/some.do",method = RequestMethod.GET)

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
    //指定other.do是POST请求方式
    @RequestMapping(value = "/other.do",method = RequestMethod.POST)
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","你好");
        mv.addObject("func","这里是doOther方法");

        mv.setViewName("other");
        return mv;
    }




}
