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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="http://localhost:8585/static/css/main.css" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="http://localhost:8585/static/javascript/main.js"></script>
    <securety:csrfMetaTags/>
</head>
<body>
    <div class="abr-content-head">
        <div class="abr-content-head-nav">
            <div>
                <p>Жанры</p>
            </div>
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="/home" class="breadcrumb">Главная</a>
                        <a href="/admin/admin" class="breadcrumb">Администирование</a>
                        <a href="#" class="breadcrumb">Жанры</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="abr-content-body">
        <c:choose>
            <c:when test="${regime == 'List'}">
                <div class="abr-list-head">
                    <div >
                        <h3>Список Жанров</h3>
                    </div>
                    <div>
                        <a href="/createGenre" class="waves-effect waves-light btn">Добавить жанр</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <div class="collection">
                        <c:forEach items="${genres}" var="genre" >
                            <a href="http://localhost:8585/editGenre?id=${genre.id}" class="collection-item">${genre.name}</a>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:when test="${regime == 'Create' || regime == 'Edit'}">
                <div class="abr-list-head">
                    <div >
                        <h3>Создать Жанр</h3>
                    </div>
                    <div>
                        <a href="/admin/genre" class="waves-effect waves-light btn">Список жанров</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <form:form method="post"
                               acceptCharset="UTF-8"
                               enctype="application/x-www-form-urlencoded"
                               modelAttribute="genreModel">
                        <form:hidden path="id"/>
                        <p>
                            <label for="name">Название жанра</label>
                            <form:input path="name" type="text"/>
                            <form:errors path="name" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="discription">Описание жанра</label>
                            <form:input path="discription" type="text"/>
                            <form:errors path="discription" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <form:errors path="errorMessage" type="text" cssClass="abr-error-message"/>
                        </p>
                        <c:if test="${regime == 'Edit'}">
                            <c:if test="${saveResult != null}">
                                <p>${saveResult}</p>
                            </c:if>
                        </c:if>
                        <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/saveGenre" >Сохранить</button>
                        <c:if test="${regime == 'Edit'}">
                            <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/deleteGenre">Удалить</button>
                        </c:if>
                        <div hidden>
                            <securety:csrfInput/>
                        </div>
                    </form:form>
                </div>
            </c:when>
            <c:otherwise>
                <p>Nothing</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
