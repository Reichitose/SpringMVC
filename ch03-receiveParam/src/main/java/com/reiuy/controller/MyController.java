package com.reiuy.controller;


import com.reiuy.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    /***
     *
     * 逐个接收请求参数
     *  要求:处理器(控制器)方法的形参名和请求中参数名必须一致
     *  同名的请求参数赋值给同名的形参
     *
     *  框架接收请求参数
     *  1.还是使用request对象接收请求参数
     *      String name = request.getParameter("name");
     *  2.springmvc框架通过中央调度器调用Mycontroller的doSome()方法
     *      调用方法是,按名称,把接收的参数赋给形参
     *
     *
     *
     */


    @RequestMapping(value = "/receiveproperty.do")

    public ModelAndView doSome(String name,Integer age){

        //因为参数被按名称的赋给了形参
        //即name->name age->age
        //就可以直接使用name和age了

        ModelAndView mv = new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");

        //返回mv
        return mv;
    }

    /**
     * 前提是逐个接收参数的情况下
     * 当请求中参数名和处理器方法的形参不同时,是无法正确赋值的,形参会全为空
     * 怎么解决这种问题呢,需要注解
     * @RequestParam 这个注解的作用是为了解决请求中参数名和形参名不一致的情况
     *              属性  1.value 请求中的参数名称
     *                   2.required 是一个boolean,默认为true
     *                   true表示请求中必须包含这个参数,没有会报错
     *                   false表示可以没有这个参数
     *              位置  在处理器方法的形参定义之前
     *
     */
    @RequestMapping(value = "/receiveparam.do")

    public ModelAndView doreceiveParam(@RequestParam(value = "rname",required = true) String name,
                                       @RequestParam(value = "rage",required = false) Integer age){
                                        //此处就相当于把将rname绑定给了name,因为只有value一个属性可以省略

        ModelAndView mv = new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");

        //返回mv
        return mv;
    }


    /**
     * 处理器方法的形参是java对象,这个对象的属性名和请求参数中的一致
     * 形参中可以有多个对象
     * 框架会创建形参的java对象,给属性赋值
     * 请求中的参数是name,框架会调用对象中的setName
     * 对象参数接收中无法使用@RequestParam
     */

    @RequestMapping(value = "/receiveobject.do")

    public ModelAndView doreceiveObject(Student myStudent){

        ModelAndView mv = new ModelAndView();

        mv.addObject("myname",myStudent.getName());
        mv.addObject("myage",myStudent.getAge());
        mv.addObject("myStudent",myStudent);

        mv.setViewName("show");

        //返回mv
        return mv;
    }

}
