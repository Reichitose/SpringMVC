package com.reiuy.handler;


import com.reiuy.exception.AgeException;
import com.reiuy.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/***
 * @ControllerAdvice
 *          叫做控制器增强,给我们的控制器类去增加功能--异常处理功能
 *      位置:  类的上方
 *      特点:  必须让框架知道这个注解所在的包名,需在springmvc配置文件声明
 *      使用了@ControllerAdvice 的包名
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //定义方法来处理发生的异常
    /***
     * 处理异常的方法和控制器方法的定义一样,可以有多个参数也可以有modelandview,
     * String,void,object类型的返回值
     *
     * 它的形参是一个Exception,用来表示controller类中抛出的异常对象
     * 通过形参获取发生的异常信息
     *
     * 方法的注解叫做@ExceptionHandler
     * 属性是异常的class,用来表示异常的类型,当发生此类型异常时,由当前方法处理
     */
    @ExceptionHandler(value = NameException.class)
    //表示当controller类中抛出NameException时由此方法处理
    public ModelAndView doNameException(Exception exception){
        //处理NameException的异常
        /**
         * 异常发生时的逻辑
         *      1.需要把异常记录下来,记录到数据库或者日志中
         *        发生的时间,发生的方法,错误内容
         *      2.发送通知,把异常的信息,通过一些途径发送给相关的开发人员
         *      3.给用户较为友好的提示,让用户知道发生了什么错误
         */

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","你的姓名必须是nb");
        mv.addObject("exception",exception);
        mv.setViewName("nameError");
        return mv;

    }


    @ExceptionHandler(value = AgeException.class)
    //表示当controller类中抛出AgeException时由此方法处理
    public ModelAndView doAgeException(Exception exception){
        //处理AgeException的异常

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","你的年龄必须小于80");
        mv.addObject("exception",exception);
        mv.setViewName("ageError");
        return mv;

    }

    //处理其他异常,NameException,AgeException以外的,我们无法预料且不知类型的异常
    @ExceptionHandler
    //不加属性时表示非指定的其他异常
    //框架会把异常优先分配给有匹配的异常处理方法,如果没有,那么分配给这个
    //这个处理方法只能有一个,负责处理所有未指定的异常
    public ModelAndView doOtherException(Exception exception){
        //处理其他异常

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","发生了意料之外的错误");
        mv.addObject("exception",exception);
        mv.setViewName("defaultError");
        return mv;

    }
}
