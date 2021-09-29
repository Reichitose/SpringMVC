ch09-Exception-@ExceptionHandler 异常处理

统一异常处理中的@ExceptionHandler注解

异常处理步骤

1.新建maven项目加入依赖

2.处理异常首先需要异常产生

3.新建一个异常类 MyUserException,并定义其子类NameException和AgeException

4.创建一个普通类作为全局异常处理类

    1.在类上方加入@ControllerAdvice注解
    
    2.在类中定义方法,方法上加入@ExceptionHandler
    
5.在controller中抛出两个异常

6.创建处理异常的视图页面

7.创建springmvc的配置文件
    注册组件扫描器扫描controller注解
    注册组件扫描器扫描controllerAdvice注解
    声明注解驱动
    
