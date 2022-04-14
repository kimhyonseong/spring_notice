<%--
  Created by IntelliJ IDEA.
  User: khs65
  Date: 2022-04-05
  Time: 오후 3:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<%--<form action="<c:url value="/register/save" />" method="post">--%>

<form:form modelAttribute="user" action="${pageContext.request.contextPath}/register/save">
    <div id="msg" class="msg"><form:errors path="id"/></div>
    <label>
        아이디
        <input type="text" name="id">
    </label>
    <label>
        비밀번호
        <input type="password" name="pw">
    </label>
    <label>
        이름
        <input type="text" name="name">
    </label>
    <label>
        생일
        <input type="text" name="birth">
    </label>
    <input type="submit" value="submit">
<%--</form>--%>
</form:form>
</body>
</html>
