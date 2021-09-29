<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/24
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>other.jsp</h3><br/>
    <!--这里使用el表达式-->
    <h3>myname:${myname}</h3><br/>
    <h3>myage:${myage}</h3>

    <!--拦截器中增加的数据-->
    <h3>mydate:${mydate}</h3>
</body>
</html>
