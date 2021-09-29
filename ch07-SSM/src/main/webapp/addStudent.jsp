<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/22
  Time: 17:16
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
    <title>注册学生</title>
    <base href="<%=basePath%>"/>
</head>
<body>
    <div align="center">
        <form action="student/addStudent.do" method="post">
            <table>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name"></td>
                    <!--这个name要跟接收对象student的形参一致-->
                </tr>
                <tr>
                    <td>年龄</td>
                    <td><input type="text" name="age"></td>
                </tr>
                <tr>

                    <td><input type="submit" value="注册"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
