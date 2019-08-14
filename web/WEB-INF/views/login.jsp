<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 07.08.2019
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="securety" uri="http://www.springframework.org/security/tags"%><!--все формы должны содержать токен-->
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Welcome!</title>
</head>
<body>

<form:form method="post"
           action="/login"
           enctype="application/x-www-form-urlencoded"
           modelAttribute="loginForm">
    <p>
        <form:input path="username" type="text" name="username"/>
        <form:errors path="username" type="text" />
    </p>
    <p>
        <form:input path="password" type="password" name="password"/>
        <form:errors path="password" type="text"/>
    </p>
    <p>
        <input type="submit" value="Login">
    </p>
    <p>
        <form:errors path="errorMessage" type="text"/>
    </p>
    <div hidden>
        <securety:csrfInput/>
    </div>
</form:form>


</body>
</html>
