<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>글쓰기</title>
</head>
<body>
<form action="<c:url value="/notice/save" />" method="post">
<div>
    <label>
        <input type="text" name="title" placeholder="제목">
    </label>
</div>
<div>
    <label>
        <textarea name="content" style="width: 500px;height: 300px;"></textarea>
    </label>
</div>
    <input type="submit">
</form>
</body>
</html>