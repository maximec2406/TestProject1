<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="securety" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 05.08.2019
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Author</title>
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
                <div>
                    <span>Авторы</span>
                </div>
            </div>
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="/home" class="breadcrumb">Главная</a>
                        <a class="breadcrumb">Авторы</a>
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
                        <h3>Список Авторов</h3>
                    </div>
                    <div>
                        <a href="/createAuthor" class="waves-effect waves-light btn">Добавить Автора</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <div class="collection">
                        <c:forEach items="${authors}" var="author" >
                            <a href="http://localhost:8585/editAuthor?id=${author.id}" class="collection-item">${author.fullName}</a>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:when test="${regime == 'Create' || regime == 'Edit'}">
                <div class="abr-list-head">
                    <div >
                        <h3>Добавить Автора</h3>
                    </div>
                    <div>
                        <a href="/author" class="waves-effect waves-light btn">Список Авторов</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <form:form method="post"
                               action="/createAuthor"
                               enctype="application/x-www-form-urlencoded"
                               modelAttribute="authorModel">
                        <form:hidden path="id"/>
                        <p>
                            <label for="first_name">Имя</label>
                            <form:input path="first_name" type="text"/>
                            <form:errors path="first_name" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="last_name">Фамилия</label>
                            <form:input path="last_name" type="text"/>
                            <form:errors path="last_name" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="patronymic">Отчество</label>
                            <form:input path="patronymic" type="text"/>
                            <form:errors path="patronymic" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="birthday">День рождения</label>
                            <form:input path="birthday" type="date"/>
                            <form:errors path="birthday" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="deathday">День смерти</label>
                            <form:input path="deathday" type="date"/>
                            <form:errors path="deathday" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="about">Об Авторе</label>
                            <form:input path="about" type="text"/>
                            <form:errors path="about" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="about">Фото</label>
                            <form:input path="photo" type="text" placeholder="http:/"/>
                            <form:errors path="photo" cssClass="abr-error-message" />
                        </p>
                        <p>
                            <label for="country">Страна</label>
                            <form:select path="country" itemValue="${authorModel.country}">
                                <form:options type="text" items="${EnumCountry}" />
                            </form:select>
                            <form:errors path="country" cssClass="abr-error-message" />
                        </p>
                        <c:if test="${regime == 'Edit'}">
                            <c:if test="${saveResult != null}">
                                <p>${saveResult}</p>
                            </c:if>
                        </c:if>
                        <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/saveAuthor" >Сохранить</button>
                        <c:if test="${regime == 'Edit'}">
                            <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/deleteAuthor">Удалить</button>
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
</body>
</html>
