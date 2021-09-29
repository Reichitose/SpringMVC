<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/20
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>show.jsp</h3><br/>
    <!--这里使用el表达式-->
    <h3>myname:${param.myname}</h3>
    <h3>myname:<%=request.getParameter("myname")%></h3>

    <br/>
    <h3>myage:${param.myage}</h3>
    <h3>mynage:<%=request.getParameter("myage")%></h3>

    <!--这里是取参数,上下两个语句的作用是一样的,都是通过在-->
</body>
</html>
