<%--
  Created by IntelliJ IDEA.
  User: khs65
  Date: 2022-04-05
  Time: 오후 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="LogOutLink" value="${sessionScope.id == null ? '/login' : '/logout'}"/>
<c:set var="LogOut" value="${sessionScope.id == null ? 'LogIn' : 'LogOut'}"/>
<html>
<head>
    <title>Home</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    header {
        width: 100%;
        height: 50px;
        background: cornflowerblue;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    header nav {
        height: 100%;
    }

    header nav section {
        display: flex;
        width: 400px;
        height: 100%;
        justify-content: space-around;
        align-items: center;
    }

    header nav section article {
        height: 100%;
        padding: 0 10px;
        align-items: center;
        display: flex;
    }

    header nav section article.on {
        background: white;
    }

    table {
        border: none;
        border-spacing: 0;
        border-collapse: collapse;
        width: 800px;
    }

    table thead {
        border-top: 3px solid black;
        border-bottom: 1px solid grey;
    }

    table tbody tr {
        border-bottom: 1px solid grey;
    }

    a {
        text-decoration: none;
    }
</style>
<body>
<script>
    let msg = "${msg}";
    if (msg == "DEL_OK") alert("삭제 완료");
    if (msg == "DEL_ERR") alert("삭제 오류");
    if (msg == "UPD_OK") alert("수정 완료");
    if (msg == "UPD_ERR") alert("수정 오류");
</script>
<header>
    <nav>
        <section>
            <article <c:if test="${noticeCode==null}">class="on"</c:if>>
                <a href="<c:url value="/"/>">HOME</a></article>
            <article <c:if test="${noticeCode==1}">class="on"</c:if>>
                <a href="<c:url value="/board/list"/>">공지사항</a></article>
            <article <c:if test="${noticeCode==2}">class="on"</c:if>>
                <a href="<c:url value="/board/list?noticeCode=2"/>">자유게시판</a></article>
            <article <c:if test="${noticeCode==3}">class="on"</c:if>>
                <a href="<c:url value="/board/list?noticeCode=3"/>">익명게시판</a></article>
        </section>
    </nav>
    <a href="${LogOutLink}" style="margin-right: 20px;">${LogOut}</a>
</header>
<main>
    <section>
        <form method="get" action="<c:url value='/board/list'/>">
            <select name="option">
                <option value="0" ${paging.sc.option == 0?"selected":""}>제목</option>
                <option value="1" ${paging.sc.option == 1?"selected":""}>내용</option>
                <option value="2" ${paging.sc.option == 2?"selected":""}>제목+내용</option>
            </select>

            <select name="noticeCode">
                <option value="0" ${paging.sc.noticeCode == 0?"selected":""}>전체</option>
                <option value="1" ${paging.sc.noticeCode == 1?"selected":""}>공지</option>
                <option value="2" ${paging.sc.noticeCode == 2?"selected":""}>자유</option>
                <option value="3" ${paging.sc.noticeCode == 3?"selected":""}>익명</option>
            </select>

            <input type="text" name="keyword" value="${paging.sc.keyword}" placeholder="검색어">
            <input type="submit" value="검색">
        </form>
    </section>
    <section>
        <div>공지사항</div>
        <table>
            <thead>
            <tr>
                <td>번호</td>
                <td>제목</td>
                <td>작성자</td>
                <td>날짜</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="notice" items="${list}">
            <tr>
                <td>${notice.noticeId}</td>
                <td><a href="<c:url value='/board/view${paging.sc.getQueryString()}&bno=${notice.noticeId}'/>">${notice.title}</a></td>
                <td><c:choose>
                    <c:when test="${notice.noticeCode==3}">
                        익명
                    </c:when>
                    <c:when test="${notice.noticeCode!=3}">
                        ${notice.writer}
                    </c:when>
                </c:choose></td>
                <td>${notice.regDate}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${paging.showPrev}">
            <a href="<c:url value='/board/list${paging.sc.getQueryString(paging.beginPage-1)}'/>"> &lt; </a>
        </c:if>
        <c:forEach var="i" begin="${paging.beginPage}" end="${paging.endPage}">
            <a href="<c:url value='/board/list${paging.sc.getQueryString(i)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${paging.showNext}">
            <a href="<c:url value='/board/list${paging.sc.getQueryString(paging.endPage+1)}'/>"> &gt; </a>
        </c:if>
    </section>
    <input type="button" value="글쓰기" onclick="location.href='<c:url value="/board/write"/>'">
</main>
</body>
</html>
