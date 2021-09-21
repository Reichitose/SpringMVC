package com.reiuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Controller
 * 用来创建处理器对象,并放在springmvc容器中
 * 在类的上面
 *
 */

@Controller
public class MyController {
    /**
     *
     * 处理用用户提交的请求,springmvc中是使用方法来处理的,这个方法是自定义的
     * 可以有多种返回值和参数,名称也是自定义的
     */

    //准备使用dosome方法处理some.do的请求
    //此时我们需要另一个注解@RequestMapping

    /**
     * @RequestMapping
     * 作用是把一个请求地址和一个方法绑定在一起,使得一个请求指定一个方法处理
     * 属性 1.value 是一个String数组,用来表示请求的uri地址(some.do),
     *              值是唯一的,不能重复,使用时推荐以/为开头
     * 位置:在方法的上面
     *      在类的上面
     *
     *      能处理请求的都是控制器(处理器),这里叫后端控制器(back controller)
     *                        区别于dispatcherservlet叫做前端控制器
     *
     *  说明  使用@RequestMapping修饰的方法叫做处理器方法,和控制器方法
     *       被修饰的方法是可以处理请求的,类似于doGet和doPost
     */

    @RequestMapping(value = "/some.do")
    //注意这里value是一个String数组可以定义多个do链接指向同一个方法执行
    /**
     * 返回值 ModelAndView 表示的是本次请求的处理结果
     * Model:数据,请求处理完成后显示给用户的数据
     * View:视图 比如jsp
     */
    public ModelAndView doSome(){
        //在dosome中处理some.do的请求
        ModelAndView mv = new ModelAndView();

        //添加数据,框架在请求的最后部分,会把数据放入request作用域
        //相当于执行了request.setAttribute
        mv.addObject("msg","hello springmvc");
        mv.addObject("func","执行了dosome方法");

        //指定视图,指定视图的完整路径
        //框架对视图执行的是forward的转发操作
        //相当于request.getRequestDispatcher("/show.jsp").forward(req.res);
        //mv.setViewName("/WEB-INF/view/show.jsp");


        //当声明了视图解析器的目录和扩展名时,就可以较简单的定义视图
        // 通过逻辑名称(文件名)指定视图
        //框架会使用视图解析器的 前缀+逻辑名称+后缀 完成路径
        //本质也是个字符串连接
        ///WEB-INF/view/ + show + .jsp

        mv.setViewName("show");



        //返回mv
        return mv;
    }

    @RequestMapping(value = "/other.do")
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","你好");
        mv.addObject("func","这里是doOther方法");

        mv.setViewName("other");
        return mv;
    }
}
