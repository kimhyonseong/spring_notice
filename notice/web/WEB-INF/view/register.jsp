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
    <div class="msg"><form:errors path="id"/>
    <label>
        아이디
        <input type="text" name="id">
    </label>
    </div>
    <div><form:errors path="pw"/>
    <label>
        비밀번호
        <input type="password" name="pw">
    </label>
    </div>
    <div><form:errors path="name"/>
    <label>
        이름
        <input type="text" name="name">
    </label>
    </div>
    <div><form:errors path="birth"/>
    <label>
        생일
        <input type="text" name="birth">
    </label>
    </div>
    <input type="submit" value="회원가입">
    <input type="button" onclick="move.back()" value="뒤로">
<%--</form>--%>
</form:form>
<script>
    let move = {
        page:"",

        back : function () {
            history.back();
        }
    }
</script>
</body>
</html>
