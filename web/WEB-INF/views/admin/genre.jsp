<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 29.08.2019
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="securety" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Genre</title>
</head>
<body>

<p>Жанры</p>
<c:choose>
    <c:when test="${regim == 'List'}">
        <p>List</p>
        <a href="/createGenre"><button>Добавить жанр</button></a>
        <c:forEach items="${genres}" var="genre" >
            <p>
                <span>${genre.name}</span><span>${genre.discription}</span>
            </p>
        </c:forEach>
    </c:when>
    <c:when test="${regim == 'Create'}">
        <p>Create</p>
        <a href="/admin/genre"><button>Список жанров</button></a>
        <p>Новый жанр</p>
        <form:form method="post"
                   action="/createGenre"
                   acceptCharset="UTF-8"
                   enctype="application/x-www-form-urlencoded"
                   modelAttribute="genreModel"
        >
            <p>
                <label for="name">Название жанра</label>
                <form:input path="name" type="text"/>
                <form:errors path="name" type="text"/>
            </p>
            <p>
                <label for="discription">Описание жанра</label>
                <form:input path="discription" type="text"/>
                <form:errors path="discription" type="text"/>
            </p>
            <p>
                <input type="submit" value="Create">
            </p>
            <div hidden>
                <securety:csrfInput/>
            </div>
        </form:form>

    </c:when>
    <c:otherwise>
        <p>Nothing</p>
    </c:otherwise>
</c:choose>

</body>
</html>
