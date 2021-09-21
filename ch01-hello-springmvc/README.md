ch01-hello-springmvc

# 需求
用户在页面发起一个请求,要交给springmvc的控制器对象,并显示处理结果

在结果页面显示一个欢迎语句

# 实现步骤
1.新建一个web maven工程

2.加入依赖   spring-webmvc依赖 此时会间接加入spring依赖
jsp依赖,servlet依赖

3.重点:在web.xml文件中注册springmvc框架的核心对象DispacherServlet

DispacherServlet叫做中央调度器,是一个servlet,父类继承Servlet

DispacherServlet也叫做前端控制器(front-controller)

负责接收用户的请求,并调用其他的控制器对象并把请求的结果显示给用户

4.创建一个发起请求的页面 index.jsp

5.创建一个控制器(处理器)类

在类的上面加入@Controller注解,创建对象并放入springmvc容器中

在类中的方法 上加入@RequestMapping注解

6.创建一个作为结果的 jsp,显示请求结果

7.创建一个springmvc的配置文件,和spring 的配置文件一样

声明组件扫描器,用来指定注解所在的包

声明视图解析器,用来帮助处理视图


#  流程
用户发起请求(some.do)

---->tomcat服务器(web.xml

--->通过url-partten知道.do类的请求给中央调度器DispatherServlet)

--->DispatherServlet ( 读取springmvc文件通过声明的组件扫描器,进行注解扫描.扫描到doSome方法对应的是some.do)

--->DispatherServlet把some.do转发给了MyController的doSome方法

--->框架执行doSome,把得到的ModelAndView进行处理,转发到show.jsp

