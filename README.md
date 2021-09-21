# 1.springmvc概述

## 概论

是基于spring的框架,实际上就是spring的一个模块,专门用于web开发,理解为servlet的升级

web开发的底层就是servlet,框架是在servlet基础之上加入一些功能,让开发人员做web开发更方便

SpringMVC就是一个spring,spring是一个容器,通过IoC可以管理对象,使用bean标签和注解

springMVC能够创建对象,放入容器中,springMVC容器

springmvc容器中放的是控制器对象@Controller,我们需要做的,就是使用@Controller创建控制器对象,然后放入springmvc容器中,把创建的对象作为容器使用,控制器对象能接受用户请求,显示处理结果

​	当做是一个servlet就行(但是不是servlet,因为使用@Controller创建的对象是普通对象, 并没有继承httpServlet类,只是由springmvc赋予了它这些功能)

## 底层实现

springmvc的底层中有一个Servlet,DispatcherServlet,

由它负责接受所有的请求,用户把请求发给DispatherServlet,DispatcherServlet再把请求转发给@Controller对象,最后由Controller处理

index.jsp--->DispatcherServlet(中央调度器,是一个servlet)-->@Controller创建的普通对象来进行处理





## 请求处理流程

用户发起请求(some.do)

---->tomcat服务器(web.xml

--->通过url-partten知道.do类的请求给中央调度器DispatcherServlet)

--->DispatcherServlet ( 读取springmvc文件通过声明的组件扫描器,进行注解扫描.扫描到doSome方法对应的是some.do)

--->DispatcherServlet把some.do转发给了MyController的doSome方法

--->框架执行doSome,把得到的ModelAndView进行处理,转发到show.jsp

可以简化为

some.do---->DispatherServlet---->MyController





## springmvc执行过程分析

1.tomcat启动创建容器的过程

​	通过load on startup指定的时间 创建DispatcherServlet对象

​	DispatcherServlet的父类继承了HttpServlet

​		被创建时会执行init(),并执行容器的创建 , 读取配置文件并执行组件扫描器,		通过扫描创建controller对象

2.请求过程

 	首先执行servlet的service方法中的doService方法, 其中会调用一个doDispatcher方法,它是中央调度器的方法, 这个方法的内部会调用controller中的处理方法来处理整个请求,也就是说controller中的处理方法是由中央调度器调用的

# 2.注解式开发

## @RequestMapping

分别可以使用在类前和方法前

类前使用可以定义模块名例如 /user



方法前用于绑定方法用来处理哪个请求.例如some.do

还可以规定方法的请求类型

有value属性,用来绑定请求名称

有produces属性,针对的是以String纯数据形式处理ajax请求时,出现中文乱码的问题

这个乱码过滤器无法解决,确切的说是不走过滤器



## 接收请求参数的两种方式

逐个接收和通过对象接收,通过对象也可以使用list和数组之类的,但是前端语法会极度复杂

## 四种返回对象

### ModelAndView

若处理器方法处理完后,需要跳转到其他资源,且又要在跳转的资源间传递数据, 此时处理器返回ModelAndView是比较好的选择, 当然, 返回ModelAndView需要在处理器方法中定义ModelAndView对象

在使用时,若该处理器方法知识进行跳转而不传递数据,或者只传递数据而不跳转(比如对页面的ajax的异步响应), 此时就不应当使用ModelAndView,因为Model和View中将总有一部分是多余的

### String

处理器方法返回的字符串可以指定逻辑视图名称,通过视图解析器解析可以将其转换成物理视图地址

返回内部资源逻辑视图名

如果要跳转的资源为内部资源,则视图解析器可以使用InternalResourceViewResolver内部资源视图解析器,此时处理器方法返回的字符串就是要跳转页面的文件名去掉扩展名后的部分,这个字符串和视图解析器中的prefix和suffix结合,形成需要访问的url



### Void

不能表示数据,也不能表示视图,只有在处理ajax时,是可以使用void,通过response对象的printwriter输出, 来响应ajax的请求

ajax只需要数据,不需要视图

### Object

这是直接返回数据的返回值,不包含视图,主要用来响应ajax请求,可以返回例如String,Integer,等等   

对象都有属性,所以相当于数据,和视图无关,所以可以使用对象的数据响应ajax请求



ajax主要使用json数据格式,处理步骤

1.加入处理json的工具库的依赖, springmvc默认使用jackson

2.把java对象转成json,我们使用一个标签,需要在springmvc的配置文件中加入

< mvc:annotation-driven >标签, 这个标签叫做注解驱动, 可以帮我们完成把java对象转成json

3.在处理器方法的上方加如一个叫做@ResponseBody的注解,可以帮我们完成输出的功能

**注解驱动**

完成java对象到json,xml,二进制等数据格式的转换

其中有个HttpMessageConverter接口, 和自己的众多的实现类 实现了这个功能

< mvc:annotation-driven > 被加入到springmvc配置文件后,会自动创建HttpMessageConverter接口的七个实现类对象

@**ResponseBody**

放在处理器方法上的,通过HttpServletResponse输出数据,响应ajax请求的,有这个标识的string是传输数据的,不属于传输视图的string类型



## 关于url-pattern

发起的请求是由哪些服务器程序处理的

http://localhost:8080/ch05_url_pattern/index.jsp :tomcat ( jsp会转为servlet ) 

http://localhost:8080/ch05_url_pattern/js/jquery-3.5.1.js  tomcat

http://localhost:8080/ch05_url_pattern/image/0.jpg  tomcat

http://localhost:8080/ch05_url_pattern/html/test.html tomcat

http://localhost:8080/ch05_url_pattern/some.do 中央调度器(springmvc框架)

这说明tomcat本身能处理静态资源的访问,想html,jpg,js文件都是静态资源文件

我们能在tomcat中找到一个defaultServlet, 它的load-on-startup是1

说明在服务器启动时这个servlet就被创建了,

这个servlet专门被用来处理**静态资源**,和处理**所有请求中未映射到其他servlet上的请求**

它的url-pattern是/

使用/后,有两种方法去访问保证访问静态资源文件不出问题

一种是使用标签 使静态资源访问的请求被转发到tomcat中的defaultservlet

一种是使用spring内置的静态资源访问servlet进行访问



## 关于路径和/的问题 (前端不加后端加)

在jsp或者html使用的地址,都是前端页面中的地址,都叫相对地址

### 地址分类

#### 1.绝对地址

指带有协议名称的, 都是绝对地址 例如http://www.baidu.com

#### 2.相对地址

只没有协议开头的, 例如test/some.do

相对地址不能独立使用,需要有一个参考地址.通过参考地址加上相对地址本身,才能够指定这个资源  相对地址+参考地址 = 绝对地址

#### 3.参考地址





#### 在你的页面中,访问地址不加 / 时

访问的是http://localhost:8080/ch06_path/index.jsp

​		路径  http://localhost:8080/ch06_path/

​		资源 index.jsp

此时,我们在index.jsp中发起  test/some.do请求

访问地址变为http://localhost:8080/ch06_path/test/some.do

​		当你的地址没有 / 开头,例如test/some.do  当你访问时

​		访问地址是当前页面的地址 ( 注意此处说的是地址 ,也可以理解为路径,即http://localhost:8080/ch06_path/) 加上链接的地址组成的完整地址

http://localhost:8080/ch06_path/ + user/some.do = http://localhost:8080/ch06_path/test/some.do

http://localhost:8080/ch06_path/,   我们就称之为相对地址



但是会出现/test/test的情况

解决方案

1.采用下面的加/方式,但是这种方式需要大量的添加

2.使用base标签

是html语言中的标签, 表示当前页面中访问地址的基地址

其意义是,指定你页面中所有没有 / 开头的地址,都以base标签中的地址为参考地址,使用base中的地址+没有/ 开头的地址,组成访问地址

在<head>中

```
<base href="http://localhost:8080/ch06_path/"/>
```



#### 在你的页面中,访问地址加 / 时

访问的是http://localhost:8080/ch06_path/index.jsp\

​		路径  http://localhost:8080/ch06_path/

​		资源 index.jsp

此时,我们在index.jsp中发起  /test/some.do请求

访问地址变成http://localhost:8080/test/some.do

参考地址是http://localhost:8080

修改方式是将请求变化为

```
<p><a href="/ch06-path/test/some.do">发起test/some.do的get请求</a> </p>
```

而这种方式本质上治标不治本,地址发生变化会导致代码中需要修改

所以我们可以采用el表达式

${pageContext.request.contextPath}  它代表项目名称

注意el表达式前没有斜杠

```
<p><a href="${pageContext.request.contextPath}/test/some.do">发起test/some.do的get请求</a> </p>
```



## 终极解决方式

```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://"+request.getServerName()
     + ":" + request.getServerPort() +request.getContextPath() + "/";
    
    //通过字符串拼接方式完成指定<base>标签内容的拼接
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>"/>
</head>
```

通过这种拼接方式定义<base>标签,使得不用加 /  

