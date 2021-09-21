<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/20
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>第一个springmvc项目</p>
    <p><a href="test/some.do">发起some.do的get请求</a> </p>
    <!--some.do前加/会报错-->

    <!--因为是post请求,使用表单-->
    <form action="test/other.do" method="post">
        <input type="submit" value="使用post发起other.do请求">
    </form>



</body>
</html>
