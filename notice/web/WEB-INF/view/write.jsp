<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="LogOutLink" value="${sessionScope.id == null ? '/login' : '/logout'}"/>
<c:set var="LogOut" value="${sessionScope.id == null ? 'LogIn' : 'LogOut'}"/>
<!doctype html>
<html lang="ko">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>글쓰기</title>
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
</head>
<body>
<header>
    <nav>
        <section>
            <article <c:if test="${notice.noticeCode==null}">class="on"</c:if>>
                <a href="<c:url value="/"/>">HOME</a></article>
            <article <c:if test="${notice.noticeCode==1}">class="on"</c:if>>
                <a href="<c:url value="/board/list"/>">공지사항</a></article>
            <article <c:if test="${notice.noticeCode==2}">class="on"</c:if>>
                <a href="<c:url value="/board/list?noticeCode=2"/>">자유게시판</a></article>
            <article <c:if test="${notice.noticeCode==3}">class="on"</c:if>>
                <a href="<c:url value="/board/list?noticeCode=3"/>">익명게시판</a></article>
        </section>
    </nav>
    <a href="${LogOutLink}" style="margin-right: 20px;">${LogOut}</a>
</header>
<form id="form">
    <div>
        <input type="hidden" name="noticeId" value="${notice.noticeId}">
        <select name="noticeCode" id="noticeCode" ${mode == "new"?"" : "disabled"}>
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
    <input type="button" value="글쓰기" onclick="notice.write()">
    <c:if test="${notice.writer == login && mode != 'new'}">
        <input type="button" value="수정" id="edit_bt" onclick="notice.edit()">
        <input type="button" value="삭제" onclick="notice.delete()">
    </c:if>
    <input type="button" value="목록" onclick="notice.list()">
</form>
<input type="text">
<div id="comment"></div>
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
            document.getElementById("title").removeAttribute("readonly");
            document.getElementById("content").removeAttribute("readonly");
            document.getElementById("noticeCode").removeAttribute("disabled");

            document.getElementById("edit_bt").setAttribute("onclick","notice.update()");
            document.getElementById("edit_bt").setAttribute("value","등록");
            document.getElementById("title").focus();
        },
        update: function () {
            if (confirm("수정하시겠습니까?")) {
                this.form.setAttribute("action", "<c:url value='/board/update'/>");
                this.form.setAttribute("method", "POST");
                this.form.submit();
            }
        },
        list: function () {
            location.href = "<c:url value='/board/list?page=${page}'/>";
        },
        delete: function () {
            if (confirm("정말 삭제하시겠습니까?")) {
                this.form.setAttribute("action", "<c:url value='/board/delete'/>");
                this.form.setAttribute("method", "POST");
                this.form.submit();
            }
        },
        reply_list : function () {
            let ajax = new XMLHttpRequest();
            ajax.open("GET","/notice/comments/${bno}",true);
            ajax.onreadystatechange = function () {
                if (ajax.DONE === ajax.readyState){
                    let status = ajax.status;

                    if (status === 0 || status === 200) {
                        let json = JSON.parse(ajax.responseText);
                        let comment = '';

                        json.forEach((val,i) => {
                            console.log(val.writer);
                            comment += `<p data-writer="\${val.writer}">\${val.comment}</p>`;
                        })
                        document.getElementById("comment").innerHTML = comment;
                    }
                }
            }
            ajax.send();
        }
    }
    notice.reply_list();
</script>
</body>
</html>