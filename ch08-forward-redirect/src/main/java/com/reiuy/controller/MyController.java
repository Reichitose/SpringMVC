package com.reiuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
//可以将这些功能全部放入test模块.例如user模块,来表明这个Controller完成的功能属于哪个部分
public class MyController {
    /**
     * 控制器方法返回modAndview,实现forward转发操作
     * 语法setViewName("forward:视图文件完整路径")
     * 特点是:不和视图解析器一同使用,就当项目中没有视图解析器
     *
     */

    @RequestMapping(value = "/doForward.do",method = RequestMethod.POST)

    public ModelAndView doForward(){

        ModelAndView mv = new ModelAndView();


        //相当于执行了request.setAttribute
        mv.addObject("msg","hello springmvc");
        mv.addObject("func","执行了doForward方法");

        //使用forward我们称为显示转发
        mv.setViewName("forward:/WEB-INF/view/show.jsp");



        //返回mv
        return mv;
    }


    /**
     *
     * 处理器方法返回modelandview  实现重定向Redirect
     * 语法  setViewName("redirect:视图完整路径")
     * 特点 不和视图解析器一同使用,就当项目中没有视图解析器
     * redirect不能访问webinf中的受保护内容
     *
     * 框架对重定向的操作:
     *          框架会把model中的简单类型数据转为字符串使用,作为重定向结果的get请求参数
     *          目的是可以在doRedirect.do和show2.jsp间传递数据
     *
     *          在目标show2.jsp页面中可以通过${param:myname}取参
     */

    @RequestMapping(value = "/doRedirect.do")

    public ModelAndView doRedirect(String name,Integer age){

        ModelAndView mv = new ModelAndView();


        //相当于执行了request.setAttribute
        mv.addObject("myname",name);
        mv.addObject("myage" ,age);

        //使用redirect进行重定向
        mv.setViewName("redirect:/show2.jsp");

        /**
         * 在原理上,redirect中发生了两次请求和两次响应,两次中的信息并不属于一个作用域
         * 重定向在原理上第一次响应只附带302状态码和需要重定向的location
         * 信息会因为不在一个作用域中而丢失
         *
         * 框架会将第一次响应中model数据中的基本类型数据转化为了字符串,并作为
         * 第二次请求中的参数附在了第二次请求之后
         * 所以第二次请求会是这样的情况
         * http://localhost:8080/ch08_forward/show2.jsp?myname=%E7%89%9B%E7%8A%87&myage=22
         * 而对于被重定向到的网页show2.jsp来讲,作用域中并没有myname和myage的信息
         * 也就是重定向的信息丢失
         * 而框架帮你把这个数据保留到了第二次请求中
         * 但知识附在了请求url的后方并没有放入作用域
         * 所以不能使用el表达式,需要通过getparam来进行参数的获取
         */



        //返回mv
        return mv;
    }

}