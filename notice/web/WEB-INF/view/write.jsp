<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <select name="noticeCode">
            <c:forEach var="codeList" items="${codeList}" varStatus="status">
                <option value="${status.count}" <c:if test="${notice.noticeCode == status.count}">selected</c:if>>
                    <c:out value="${codeList}"/>
                </option>
            </c:forEach>
        </select>
        <label>
            <input type="text" name="title" id="title" placeholder="제목" value="${notice.title}"
                   ${mode == "new"?"" : "readonly"}>
        </label>
    </div>
    <div>
        <label>
            <textarea name="content" style="width: 500px;height: 300px;" id="content"
            ${mode == "new"?"" : "readonly"}>${notice.content}</textarea>
        </label>
    </div>
    <input type="button" value="등록" onclick="notice.write()">
    <input type="button" value="수정" onclick="">
    <input type="button" value="삭제" onclick="notice.delete()">
    <input type="button" value="목록" onclick="notice.list()">
</form>
<script>
    let notice = {
        form: document.getElementById("form"),
        write: function () {
            <c:choose>
            <c:when test="${empty writer}">
            location.href = "<c:url value="/board/write"/>";
            </c:when>
            <c:when test="${writer ne null}">
            this.form.setAttribute("action", "<c:url value='/board/write'/>");
            this.form.setAttribute("method", "POST");
            this.form.submit();
            </c:when>
            </c:choose>
        },
        edit: function () {

        },
        update: function () {
            this.form.setAttribute("action", "<c:url value='/board/update'/>");
            this.form.setAttribute("method", "POST");
            this.form.submit();
        },
        list: function () {
            location.href = "<c:url value='/board/list?currentPage=${currentPage}'/>";
        },
        delete: function () {
            this.form.setAttribute("action", "<c:url value='/board/delete'/>");
            this.form.setAttribute("method", "POST");
            this.form.submit();
        }
    }
</script>
</body>
</html>