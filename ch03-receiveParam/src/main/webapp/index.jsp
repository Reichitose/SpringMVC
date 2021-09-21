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

    <p>逐个接收中请求中参数名和处理器方法形参名一样的情况</p>
    <form action="receiveproperty.do" method="post">

        姓名<input type="text" name="name"><br/>
        年龄<input type="text" name="age">
        <input type="submit" value="提交参数">
    </form>
<br/>
    <p>逐个接收中请求中参数名和处理器方法形参不同的情况</p>
    <form action="receiveparam.do" method="post">

        姓名<input type="text" name="rname"><br/>
        年龄<input type="text" name="rage">
        <input type="submit" value="提交参数">
    </form>
<br/>

    <p>使用java对象接收请求参数</p>
    <form action="receiveobject.do" method="post">

        姓名<input type="text" name="name"><br/>
        年龄<input type="text" name="age">
        <input type="submit" value="提交参数">
    </form>



</body>
</html>
