<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 13.08.2019
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>

<p>New User Data</p>

<form:form method="post"
           action="/createUser"
           enctype="application/x-www-form-urlencoded"
           modelAttribute="newUser">
    <p>
        <label for="email">Email</label>
        <form:input path="email" type="text" name="email"/>
        <form:errors path="email" type="text"/>
    </p>
    <p>
        <label for="email">Имя</label>
        <form:input path="first_name" type="text" name="first_name"/>
        <form:errors path="first_name" type="text"/>
    </p>
    <p>
        <label for="email">Фамилия</label>
        <form:input path="last_name" type="text" name="last_name"/>
        <form:errors path="last_name" type="text"/>
    </p>
    <p>
        <label for="email">День рождения</label>
        <form:input path="birthday" type="date" name="birthday"/>
        <form:errors path="birthday" type="text"/>
    </p>
    <p>
        <label for="email">Nickname</label>
        <form:input path="nickname" type="text" name="nickname"/>
        <form:errors path="nickname" type="text"/>
    </p>
    <p>
        <label for="email">Пароль</label>
        <form:input path="password" type="password" name="password"/>
        <form:errors path="password" type="text"/>
    </p>
    <p>
        <label for="email">Проверка пароля</label>
        <form:input path="secPassword" type="password" name="secPassword"/>
        <form:errors path="secPassword" type="text"/>
    </p>
    <p>
        <label for="email">Информация</label>
        <form:input path="about" type="text" name="about"/>
        <form:errors path="about" type="text"/>
    </p>
    <p>
        <label for="email">Фото(пока нет)</label>
        <form:input path="photo" type="text" name="photo"/>
        <form:errors path="photo" type="text"/>
    </p>
    <p>
        <input type="submit" value="Create">
    </p>
    <p>
        <form:errors path="errorMessage" type="text"/>
    </p>
</form:form>

</body>
</html>
