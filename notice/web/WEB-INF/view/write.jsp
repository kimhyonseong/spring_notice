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
<form id="form">
<div>
    <input type="hidden" name="noticeId" value="${notice.noticeId}">
    <input type="hidden" name="writer" value="${notice.writer}">
    <label>
        <input type="text" name="title" id="title" placeholder="제목" value="${notice.title}" readonly>
    </label>
</div>
<div>
    <label>
        <textarea name="content" style="width: 500px;height: 300px;" id="content" readonly>${notice.content}</textarea>
    </label>
</div>
    <input type="button" value="등록" onclick="notice.write()">
    <input type="button" value="수정" onclick="">
    <input type="button" value="삭제" onclick="">
    <input type="button" value="목록" onclick="notice.list()">
</form>
<script>
    let notice = {
        form : document.getElementById("form"),
        write : function () {
            this.form.setAttribute("action","<c:url value='/board/save'/>");
            this.form.setAttribute("method","POST");
            this.form.submit();
        },
        edit : function () {

        },
        update : function () {
            this.form.setAttribute("action","<c:url value='/board/update'/>");
            this.form.setAttribute("method","POST");
            this.form.submit();
        },
        list : function () {
            location.href = "<c:url value='/board/list?currentPage=${currentPage}'/>";
        }
    }
</script>
</body>
</html>