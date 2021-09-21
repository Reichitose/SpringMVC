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
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("button").click(function () {
                //alert("button click");
                $.ajax({
                    //url:"returnVoid-ajax.do"
                    //url:"returnStudentJsonArray.do",
                    url:"returnStringData.do",
                    data:{
                        name:"zhangsan",
                        age:20
                    },
                    type:"post",
                    //dataType:"json",
                    success:function (resp) {
                        //对于数组,我们需要用循环
                        //alert(resp.name+"   "+resp.age);
                        //$.each(resp,function (i,n) {
                            //alert(n.name+"   "+n.age);

                        //})
                        alert("返回了文本数据"+resp);
                    }
                })
            })
        })

    </script>
</head>
<body>

    <p>处理器方法返回string用来表示视图名称</p>
    <form action="returnString-view.do" method="post">

        姓名<input type="text" name="name"><br/>
        年龄<input type="text" name="age">
        <input type="submit" value="提交参数">
    </form>

    <br/>
    <br/>

    <button id="btn">发起ajax请求</button>




</body>
</html>
