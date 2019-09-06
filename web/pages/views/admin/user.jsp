<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="securety" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8585/static/css/main.css" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="http://localhost:8585/static/javascript/main.js"></script>
    <securety:csrfMetaTags/>
</head>
<body>
    <div class="abr-content-head">
        <div class="abr-content-head-nav">
            <div>
                <p>Пользователи</p>
            </div>
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="/home" class="breadcrumb">Главная</a>
                        <a href="/admin/admin" class="breadcrumb">Администирование</a>
                        <a href="/admin/user" class="breadcrumb">Пользователи</a>
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
                        <h3>Список Пользователей</h3>
                    </div>
                    <div>
                        <a href="/createUser" class="waves-effect waves-light btn">Добавить пользователя</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <div class="collection">
                        <c:forEach items="${users}" var="user" >
                            <a href="http://localhost:8585/editUser?id=${user.id}" class="collection-item">${user.email}</a>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:when test="${regime == 'Create' || regime == 'Edit'}">
                <div class="abr-list-head">
                    <div >
                        <h3>Создать Пользователя</h3>
                    </div>
                    <div>
                        <a href="/admin/user" class="waves-effect waves-light btn">Список пользователей</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <form:form method="post"
                               modelAttribute="userModel"
                               enctype="application/x-www-form-urlencoded">
                        <form:hidden path="id"/>
                        <p>
                            <label for="email">Email</label>
                            <form:input path="email" type="text"/>
                            <form:errors path="email" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="first_name">Имя</label>
                            <form:input path="first_name" type="text" />
                            <form:errors path="first_name" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="last_name">Фамилия</label>
                            <form:input path="last_name" type="text" name="last_name"/>
                            <form:errors path="last_name" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="birthday">День рождения</label>
                            <form:input path="birthday" type="date" name="birthday"/>
                            <form:errors path="birthday" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="nickname">Nickname</label>
                            <form:input path="nickname" type="text" name="nickname"/>
                            <form:errors path="nickname" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="password">Пароль</label>
                            <form:input path="password" type="password" name="password"/>
                            <form:errors path="password" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="secPassword">Проверка пароля</label>
                            <form:input path="secPassword" type="password" name="secPassword"/>
                            <form:errors path="secPassword" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="about">Информация</label>
                            <form:input path="about" type="text" name="about"/>
                            <form:errors path="about" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="photo">Фото(пока нет)</label>
                            <form:input path="photo" type="text" name="photo"/>
                            <form:errors path="photo" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <label for="role">Роль</label>
                            <div class="input-field col s12">
                                <form:select path="role" itemValue="${newUser.role}" >
                                    <form:options type="text" items="${Roles}" />
                                </form:select>
                            </div>
                            <form:errors path="role" type="text" cssClass="abr-error-message"/>
                        </p>
                        <p>
                            <form:errors path="errorMessage" type="text" cssClass="abr-error-message"/>
                        </p>
                        <c:if test="${regime == 'Edit'}">
                            <c:if test="${saveResult != null}">
                                <p>${saveResult}</p>
                            </c:if>
                        </c:if>
                        <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/saveUser" >Сохранить</button>
                        <c:if test="${regime == 'Edit'}">
                            <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/deleteUser">Удалить</button>
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

