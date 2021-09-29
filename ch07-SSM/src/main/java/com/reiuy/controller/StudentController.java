package com.reiuy.controller;
import com.reiuy.entity.Student;
import com.reiuy.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService service;

    //注册学生
    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        ModelAndView mv = new ModelAndView();

        String tips = "";
        System.out.println(student);
        //调用service处理
        int nums = service.addStudent(student);

        if (nums > 0){
            //返回值大于0 说明注册成功
            tips = "学生"+student.getName()+"  注册成功!";
        }else{
            tips = "注册失败";
        }
        //添加数据
        mv.addObject("tips",tips);
        //指定结果页面
        mv.setViewName("result");
        return mv;
    }



    //处理学生的查询,响应ajax
    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        List<Student> students = service.foundStudents();
        return students;    //会被框架转为数组并被作为json写入响应体
    }
}
