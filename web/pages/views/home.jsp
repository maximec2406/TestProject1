
<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 13.08.2019
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<p>Home</p>
<a href="/book">Книги</a>
<a href="/author">Авторы</a>
<a href="/friends">Друзья</a>
<a href="/recomends">Рекомендации</a>
<a href="/search">Поиск</a>
<c:if test="${ROLE=='ADMIN'}">
    <a href="/admin/admin">Админка</a>
</c:if>
<c:if test="${ROLE=='USER'}">
    <p>Пользователь</p>
</c:if>
</body>
</html>
