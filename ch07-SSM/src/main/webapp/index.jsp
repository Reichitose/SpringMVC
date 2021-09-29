<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/22
  Time: 17:12
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
    <title>功能目录</title>
    <base href="<%=basePath%>"/>
</head>
<body>
<center>
    <p>SSM整合</p>
    <table>
        <tr>
            <td><a href="addStudent.jsp"> 注册学生</a></td>
        </tr>
        <tr>
            <td><a href="listStudent.jsp">浏览学生</a></td>
        </tr>
    </table>
</center>
</body>
</html>
