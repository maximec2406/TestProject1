<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<p>Пользователь</p>
<c:choose>
    <c:when test="${regim == 'List'}">
        <p>List</p>
        <a href="/createUser"><button>Добавить пользователя</button></a>
        <c:forEach items="${users}" var="user" >
            <p>
                <span>${user.email}</span>
            </p>
        </c:forEach>
    </c:when>
    <c:when test="${regim == 'Create'}">
        <p>Create</p>
        <a href="/admin/user"><button>Список пользователей</button></a>
        <form:form method="post"
                   action="/createUser"
                   modelAttribute="userModel"
                   enctype="application/x-www-form-urlencoded">
            <p>
                <label for="email">Email</label>
                <form:input path="email" type="text"/>
                <form:errors path="email" type="text"/>
            </p>
            <p>
                <label for="first_name">Имя</label>
                <form:input path="first_name" type="text" />
                <form:errors path="first_name" type="text"/>
            </p>
            <p>
                <label for="last_name">Фамилия</label>
                <form:input path="last_name" type="text" name="last_name"/>
                <form:errors path="last_name" type="text"/>
            </p>
            <p>
                <label for="birthday">День рождения</label>
                <form:input path="birthday" type="date" name="birthday"/>
                <form:errors path="birthday" type="text"/>
            </p>
            <p>
                <label for="nickname">Nickname</label>
                <form:input path="nickname" type="text" name="nickname"/>
                <form:errors path="nickname" type="text"/>
            </p>
            <p>
                <label for="password">Пароль</label>
                <form:input path="password" type="password" name="password"/>
                <form:errors path="password" type="text"/>
            </p>
            <p>
                <label for="secPassword">Проверка пароля</label>
                <form:input path="secPassword" type="password" name="secPassword"/>
                <form:errors path="secPassword" type="text"/>
            </p>
            <p>
                <label for="about">Информация</label>
                <form:input path="about" type="text" name="about"/>
                <form:errors path="about" type="text"/>
            </p>
            <p>
                <label for="photo">Фото(пока нет)</label>
                <form:input path="photo" type="text" name="photo"/>
                <form:errors path="photo" type="text"/>
            </p>
            <p>
                <label for="role">Роль</label>
                <form:select path="role" itemValue="${newUser.role}" >
                    <form:options type="text" items="${Roles}" />
                </form:select>
                <form:errors path="role" type="text"/>
            </p>
            <p>
                <input type="submit" value="Create">
            </p>
            <p>
                <form:errors path="errorMessage" type="text"/>
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

