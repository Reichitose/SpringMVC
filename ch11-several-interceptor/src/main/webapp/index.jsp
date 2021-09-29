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
    <title>一个拦截器</title>
    <base href="<%=basePath%>"/>
</head>
<body>
<p>拦截器</p>
<form action="some.do" method="post">
    姓名<input type="text" name="name">
    年龄<input type="text" name="age">
    <input type="submit" value="提交请求">
</form>

</body>
</html>
