<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 29.08.2019
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrative page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="http://localhost:8585/static/css/main.css" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="http://localhost:8585/static/javascript/main.js"></script>
</head>
<body>
    <div class="abr-content-head">
        <div class="abr-content-head-nav">
            <div>
                <p>Администрирование</p>
            </div>
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="/home" class="breadcrumb">Главная</a>
                        <a href="/admin/admin" class="breadcrumb">Администирование</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="abr-content-body">
        <div class="abr-list-body">
            <ul class="collection">
                <a href="/admin/genre" class="collection-item">Жанры</a>
                <a href="/admin/user" class="collection-item">Пользователи</a>
            </ul>
        </div>
    </div>
</body>
</html>
