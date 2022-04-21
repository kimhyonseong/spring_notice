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
<header>
    <nav>
        <section>
            <article><a href="<c:url value="/"/>">HOME</a></article>
            <article class="on"><a href="">공지사항</a></article>
            <article><a href="">자유게시판</a></article>
            <article><a href="">익명게시판</a></article>
        </section>
    </nav>
    <a href="${LogOutLink}" style="margin-right: 20px;">${LogOut}</a>
</header>
<main>
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
                <td>${notice.title}</td>
                <td>${notice.writer}</td>
                <td>${notice.regDate}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${paging.showPrev}">
            <a href="<c:url value='/board/list?currentPage=${paging.beginPage-1}'/>"> &lt; </a>
        </c:if>
        <c:forEach var="i" begin="${paging.beginPage}" end="${paging.endPage}">
            <a href="<c:url value='/board/list?currentPage=${i}'/>">${i}</a>
        </c:forEach>
        <c:if test="${paging.showNext}">
            <a href="<c:url value='/board/list?currentPage=${paging.endPage+1}'/>"> &gt; </a>
        </c:if>
    </section>
    <input type="button" value="글쓰기" onclick="location.href='<c:url value="/board/write"/>'">
</main>
</body>
</html>
