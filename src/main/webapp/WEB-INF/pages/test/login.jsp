<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/common.jsp"%>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="/login" method="post">
    用户名：<input type="text" title="username" name="username" value="<shiro:principal/>"><br/>
    密码：<input type="password" name="pssword" title="password"><br/>
    <%-- jcaptchaEbabled 在JCaptchaValidateFilter设置 --%>
    <c:if test="${jcaptchaEbabled}">
        验证码：
        <input type="text" title="jcaptchaCode">
        <img class="jcaptcha-btn jcaptcha-img" src="${pageContext.request.contextPath}/jcaptcha.jpg" title="点击更换验证码">
        <a class="jcaptcha-btn" href="javascript:;">换一张</a>
        <br/>
    </c:if>
    自动登录：<input type="checkbox" title="rememberMe" value="true"><br/>
    <input type="submit" value="登录">
</form>
<form action="/logout" method="post">
    <input name="登出" type="submit">
</form>
<button id="lo" >lo</button>
<script src="/static/admin/js/jquery-1.7.2.min.js"></script>
<script>
    $(function() {
        $(".jcaptcha-btn").click(function() {
            $(".jcaptcha-img").attr("src", '${pageContext.request.contextPath}/jcaptcha.jpg?'+new Date().getTime());
        });
    });
    $('#lo').on('click', function (e) {
        //判断至少写了一项

            $.ajax({
                type: "POST",
                url: "/login",
                data: {
                    "username":"18819259469",
                    "password":"18819259469"
                },
                dataType: "json",
        })
    });
</script>
</body>
</html>