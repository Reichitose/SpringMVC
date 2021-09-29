<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/24
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登出</title>
</head>
<body>
    退出系统,从session中删除数据
    <%
        session.removeAttribute("name");
    %>
</body>
</html>
