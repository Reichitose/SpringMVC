<%--
  Created by IntelliJ IDEA.
  User: RE1UY
  Date: 2021/9/23
  Time: 8:19
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
    <title>使用ajax查询学生</title>
    <base href="<%=basePath%>"/>
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            loadStudentData();
            //在dom页面加载后执行loadStudentData()函数
            $("#btnloader").click(function () {
                loadStudentData();
            })
        })
        
        function loadStudentData() {
            $.ajax({
                url:"student/queryStudent.do",
                type:"get",
                dataType:"json",
                success:function (data) {
                    //清掉旧数据
                    $("#info").html("")
                    //增加新数据
                    //此时data是json数组中的 Object对象
                    $.each(data,function(i,n){
                        $("#info").append("<tr>")
                            .append("<td>"+n.id+"</td>")
                            .append("<td>"+n.name+"</td>")
                            .append("<td>"+n.age +"</td>")
                            .append("</tr>")
                    })
                }
            })
        }
    </script>
</head>
<body>
    <div align="center">
        <table>
            <thead>
            <tr>
                <td>学号</td>
                <td>姓名</td>
                <td>年龄</td>
            </tr>
            </thead>
            <tbody id="info"></tbody>
        </table>
        <input type="button" id="btnloader" value="数据查询">
    </div>
</body>
</html>
