package com.reiuy.Handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//高版本中只需要根据需求实现方法就行了,不需要一次实现所有的方法
//拦截器类,用来拦截用户的请求
public class MyiInterception implements HandlerInterceptor {

    //定义一个成员变量用来完成afterHandle中的计时功能
    private long start_time = 0;

    /**
     * preHandle叫做预处理方法
     *
     * 这个方法很重要,请求是需要从这里进入
     * 相当于整个项目的入口和门卫
     * 参数:
     *   @param request   请求
     *   @param response  应答
     *   @param handler  这个对象是被拦截的控制器对象
     * 返回值
     *   @return
     *   是一个bool值,有true和false
     *   true表示请求通过了拦截器,可以执行控制器方法
     *   false 请求没有通过拦截器的验证,请求被截断,没有被处理
     *         如果返回false,后续的所有拦截器都不会执行
     *
     *
     *   @throws Exception
     *
     *   特点
     *   1.该方法是在控制器方法(MyController的doSome)之前先执行的
     *      用户的请求会首先到达这个方法
     *   2.在这个方法中,可以获取请求的信息,可以验证这个请求是否符合要求
     *     可以验证用户是否登录,用户是否有权限访问某个地址(url)
     *     如果验证失败,我们可以截断请求,请求不能被处理
     *     如果验证成功,我们可以放行请求,此时控制器方法才能执行
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        start_time = System.currentTimeMillis();
        //给time赋值当前的系统时间

        System.out.println("这里是MyiInterception的preHandle");
        //在方法内部有逻辑,根据结果返回true和false
        //拦截请求并给浏览器一个返回结果
        //request.getRequestDispatcher("/tips.jsp").forward(request,response);
        return true;
    }

    /**
     * postHandle叫做后处理方法
     *
     * 参数
     *  @param request
     *  @param response
     *  @param handler  被拦截的处理器对象
     *  @param modelAndView 处理器方法的返回值 在这里是doSome方法返回的mv
     *
     * 特点
     * 1.是在处理器方法之后执行的,所以叫后处理
     * 2.能够获取到处理器方法的返回值modelandview,可以修改ModelAndView中的数据和视图
     *   可以影响到最后的执行结果
     * 3.主要是对原来的结果进行二次修正
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("这里是MyiInterception的postHandle");
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


    /**
     * afterCompletion叫 最后执行方法
     *
     * 参数
     * @param handler 被拦截的处理器对象
     * @param ex  程序中发生的异常
     * 没有返回值
     *
     * 特点
     *  1.在请求处理完成后执行
     *   在框架中规定是当视图处理完成后,对视图执行了forward,就视为请求处理完成
     *  2.一般是用来做资源回收的,在程序的请求过程中创建了一些对象,在这里可以删除
     *    回收占用的内存
     */



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这里是MyiInterception的afterCompletion");

        long end_time = System.currentTimeMillis();
        System.out.println("从prehandle到请求处理完成的时间:"+(end_time-start_time));

    }
}
