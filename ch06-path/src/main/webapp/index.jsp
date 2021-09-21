<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/20
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
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
<body>
    <p>第一个springmvc项目</p>
    <p><a href="test/some.do">发起test/some.do的get请求</a> </p>
    <!--注意el表达式前方没有/ -->



</body>
</html>
