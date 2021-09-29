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
 
 
 # 3.SpringMVC的其他核心技术

## 请求转发和重定向

> *WEB-INF*页面只对服务端开放，对客户端是不可见的

forward:表示转发,实现request.getDispatcher("xxx.jsp").forward, 因为过程是在服务器内部进行, 所以可以访问web-inf中受保护的文件

使用forward的转发我们称为显示转发

用于需要转发到视图解析器所规定范围之外的网页

```
mv.setViewName("forward:/WEB-INF/view/show.jsp");
/**
* 控制器方法返回modAndview,实现forward转发操作
* 语法setViewName("forward:视图文件完整路径")
* 特点是:不和视图解析器一同使用,就当项目中没有视图解析器
*/
```



redirect:表示重定向,实现response.sendRedirect("xxx.jsp"),因为过程是用户浏览器和服务器的两个资源间的两次请求响应,所以不能访问web-inf中受保护的文件

```
mv.setViewName("redirect:/show2.jsp");
/**
*
* 处理器方法返回modelandview  实现重定向Redirect
* 语法  setViewName("redirect:视图完整路径")
* 特点 不和视图解析器一同使用,就当项目中没有视图解析器
* redirect不能访问webinf中的受保护内容
*
* 框架对重定向的操作:
*		框架会把model中的简单类型数据转为字符串使用,作为重定向结果的get请求参数
*          目的是可以在doRedirect.do和show2.jsp间传递数据
*
*          在目标show2.jsp页面中可以通过${param:myname}取参
*/
```



## 异常处理

思想来自于aop,原因是传统异常的处理(try , catch )和业务逻辑的耦合度较高,框架提供了解决方案, 将业务逻辑代码和异常处理代码分离, 解耦合

**统一全局异常处理**

把controller中的所有异常处理都集中到一个地方,由这一个地方处理整个项目中所有的异常

使用两个注解

@ControllerAdvice

```
/***
 * @ControllerAdvice
 *          叫做控制器增强,给我们的控制器类去增加功能--异常处理功能
 *   位置:  类的上方
 *   特点:  必须让框架知道这个注解所在的包名,需在springmvc配置文件声明
 *   使用了@ControllerAdvice 的包名
 */
```

@ExceptionHandler

```
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
```

## 拦截器

是springmvc中的一种对象, 需要实现HandlerInterceptor接口

和过滤器类似,但侧重点和功能的方向不同

但是过滤器做的是过滤请求参数设置编码字符集等工作 , 而拦截器针对的是请求,对请求进行预先的判断和处理

拦截器的特点

​	拦截器是全局的,可以对多个controller做拦截

一个项目中可以有0个,1个或者多个拦截器, 他们在一起拦截用户 的请求

拦截器常用于将用户的登录处理 , 权限的检查, 记录日志

利用的是aop思想,把相同的功能从controller中剥离出来, 集中到拦截器进行处理

**使用步骤:**

1.定义类实现HandlerInterceptor接口

2.在springmvc的配置文件中声明拦截器,让框架知道拦截器的存在

**执行时间:**

1.请求处理之前, 也就是controller类中的方法执行之前先进行拦截(首次)

2.在控制器方法执行之后也会执行拦截器

3.在请求处理完成后也会执行拦截器

一共是三个方法,执行时间和功能不同

**preHandle** 

请求首先会从这里进入,经过判断,由返回值true和false决定是否继续执行该请求

**postHandle**

在控制器方法处理完毕后,会被这个方法拦截, 在这个方法中可以对请求处理结果的数据和视图进行修改,数据是添加,视图是修改,没有返回值

**afterCompletion**

在请求处理完成,也就是说在视图被forward之后这个方法会被执行, 一般用来做资源回收,没有返回值



**多个拦截器**

框架中多个拦截器的保存是通过ArrayList
按照声明的先后放入
第一个声明的是0号成员
第二个声明的是1号成员

在先声明1的情况中

执行1的pre-->执行2的pr-->当两个都返回true-->执行处理器方法-->执行2的post-->执行1的post-->执行2的after-->执行1的after

当存在两个pre的返回值不一致时,仍会按照优先级的顺序顺序执行,也就是说,在1返回值为true,2返回值为false的情况时, 请求进入处理器方法会被2截断,导致1即使是true也无法继续执行处理器方法,所以1 会执行自己的after并直接结束

当1的pre为false时,请求会被直接截断,无论之后是否有2或者2的pre是否为false,都没有机会执行

这个过程我们称为拦截器链

我们可以通过两种方式理解这个顺序

第一种是先声明的拦截器具有较高的优先级,无论如何,他都会对请求的执行有最高级优先级,由先声明的第一次判断是否放行,然后在post做结果的修正时,由他最后进行修正,保证视图和数据最后是由他修改的,而在after中也由他最后进行处理



第二种,我们将1和2两个拦截器理解为套在Controller上的两层壳 , 请求要被处理,视贯穿 ,首先要经过最外层的1,然后经过2,处理完成后,先经过2,再经过1



这里是MyiInterception1的preHandle       

这里是MyiInterception2的preHandle

 ====执行了doSome方法===

这里是MyiInterception2的postHandle

这里是MyiInterception1的postHandle

这里是MyiInterception2的afterCompletion

这里是MyiInterception1的afterCompletion



简而言之,就是先声明谁谁先开始执行,谁先声明谁优先级高,但是所有拦截器都对是否处理请求有一票否决权,只要 有一个pre的返回值为false,则请求一定会被截断



拦截器的使用中,我们同样是定义多个拦截器分别负责不同的功能,这些功能也是通过优先级来决定声明的先后



**拦截器和过滤器的区别**

1.过滤器是servlet规范中的对象, 而拦截器是框架中定义的对象,离开了框架就不存在了

2.过滤器用来实现filter接口的, 拦截器是实现HandlerInterceptor接口

3.过滤器是用来去设置请求对象request和响应对象response的参数,属性的,侧重于对数据的过滤

拦截器是用来验证请求的,能截断请求

4.过滤器是在拦截器之前先执行的

5.过滤器是tomcat服务器创建的对象, 拦截器是springmvc容器中创建的对象

6.过滤器只有一个执行时间点,拦截器有三个,pre,post,after

7.过滤器可以处理jsp,js,html等, 拦截器侧重于拦截对Controller对象的请求

如果请求不被DispatcherServlet接收, 那么这个请求不会被拦截器拦截

8.拦截器拦截普通类方法执行, 过滤器过滤servlet请求响应





# 4.SpringMVC执行流程



1 用户发起请求some.do

2.DispatcherServlet中央调度器接收请求some.do

​	将请求转交给**处理器映射器(HandleMapping)**

> 他是框架中的对象,把HandlerMapping接口的类都叫映射器(有多个)
>
> 他可以根据请求从springmvc容器中获取处理器对象(getBean)
>
> 当我们使用注解时,使用的处理器映射器是RequestMappingHandlerMapping类

他把找到的处理器对象放到了**处理器执行链**的类中保存

> 这个类中保存着处理器对象(MyController)
>
> 和项目中所有的拦截器(Interceptor)

执行链被返回给中央调度器

3.DispatcherServlet中央调度器把执行链中的处理器对象交给了**处理器适配器**对象(多个)

>他是框架中的对象,是springmvc框架中的对象,需要实现HandlerAdapyer接口
>
>他的作用是执行处理器方法,调用MyController.doSome()并得到返回值ModelAndView

他得到的结果ModelAndView被返回给中央调度器,这个view是逻辑名称

4.DispatcherServlet中央调度器将modeandview交给**视图解析器**对象

>springmvc中实现了ViewResoler接口的对象(可以有多个)
>
>作用:组成视图的完整路径,使用前缀prefix和后缀suffix,并创建该视图的View对象,返回给中央调度器
>
>View是一个接口,表示视图的, 在框架中jsp,html不是String表示,而是通过View和它的实现类表示视图
>
>视图对象的内部有一个属性叫url, 有完整的路径
>
>jsp文件由InternalResourceView视图类表示

5.DispatcherServlet中央调度器把创建的View对象获取到,调用View类自己的方法,并把Model数据放入request作用域 , 即将数据填充入视图, 执行视图的forward,请求结束

6.返回中央调度器形成响应对象并响应

 



