package com.reiuy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reiuy.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    /**
     * 处理器方法返回string 表示逻辑视图名称,需要配置视图解析器
     */
    @RequestMapping(value = "/returnString-view.do")
    public String doreturnView(String name,Integer age){
        //如果还需要处理数据,可以使用setAttribute方法手动添加

        //show:视图的逻辑名称,和项目中视图解析器的prefix和suffix拼接成url
        //框架会执行一个forwarc转发操作
        //如果要表示完整视图路径,则不能配置视图解析器,
        return "show";
    }



    /***
     * 处理器方法返回void,处理ajax请求
     *
     * 手动处理ajax,json数据,代码有大量重复
     * 包括java对象转json和通过httpservletresponse输出json
     */

    @RequestMapping(value = "/returnVoid-ajax.do")
    public void doreturnVoidAjax(HttpServletResponse response,String name, Integer age) throws IOException {
        System.out.println(name + age);

        //处理ajax使用json做数据格式
        //模拟service调用完成了,使用student表示结果
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        String json = "";
        //把结果的对象转成json格式的数据
        if(student != null) {
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转换的json  "+json);
        }
        //输出数据.响应请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }

    /**
     * 处理器方法返回一个Student,通过框架转换为json,响应ajax请求
     *
     * @ResponseBody
     *    这个注解的作用是把我们处理器方法返回的对象转为json后
     *    通过HttpServletResponse输出给浏览器
     *    位置:在方法定义之上,和其他注解没有顺序之分
     *
     *
     */

    @RequestMapping(value = "/returnStudentJson.do")
    @ResponseBody
    public Student dorStudentJsonObject(String name, Integer age){
        //模拟调用service,student对象表示结果
        Student student = new Student();
        student.setName("李四");
        student.setAge(20);
        return student;
        //会被框架转为json

    }

    /**
     * 处理器方法返回一个json数组
     * 返回值是多个对象时
     */

    @RequestMapping(value = "/returnStudentJsonArray.do")
    @ResponseBody
    public List<Student> dorStudentJsonArray(String name, Integer age){
        //模拟调用service,list<student>对象表示结果

        List<Student> list = new ArrayList<>();

        Student student = new Student();
        student.setName("李四");
        student.setAge(20);
        list.add(student);

        student = new Student();
        student.setName("张三");
        student.setAge(22);
        list.add(student);


        return list;
        //会被框架转为json数组

    }

    /**
     * 处理器方法返回的是String,这里表示数据,而不是视图
     * 区分是视图还是数据主要看有没有@ResponseBody
     * 有就是数据,没有就是视图
     *
     *
     * 默认会使用8859-1编码作为ContentType,导致中文的乱码
     * 解决方案就是给@RequestMapping添加一个属性 produces
     * 指定新的contentType
     */
    @ResponseBody
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    public String doStringData(String name,Integer age){
        return "hello springmvc";
    }
}
