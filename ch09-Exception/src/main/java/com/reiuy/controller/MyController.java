
package com.reiuy.controller;


import com.reiuy.exception.AgeException;
import com.reiuy.exception.MyUserException;
import com.reiuy.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MyController {


    @RequestMapping(value = "/some.do")

    public ModelAndView doSome(String name,Integer age) throws MyUserException {

        ModelAndView mv = new ModelAndView();
        //根据请求参数抛出异常
        //前置!用来取反,表示name不为nb时抛出异常
        if(!"nb".equals(name)){
            throw new NameException("姓名不正确");
        }
        if(age == null || age > 80){
            throw new AgeException("年龄太大");
        }
        //这里是把异常抛出给框架,而不是自己用trycatch处理

        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");



        //返回mv
        return mv;
    }

}

