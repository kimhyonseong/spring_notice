<%--
  Created by IntelliJ IDEA.
  User: khs65
  Date: 2022-04-06
  Time: 오전 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="<c:url value="/login" />">
    <label>ID
        <input type="text" name="id" value="">
    </label>
    <label>PW
        <input type="password" name="pw" value="">
    </label>
    <input type="button" class="register" value="회원가입">
    <input type="button" class="find" value="아이디/비밀번호 찾기">
    <input type="submit" value="ok">
</form>
</body>
<script>
    function register() {
        location.href = "<c:url value='/register/add'/>";
    }

    function find() {

    }
</script>
</html>