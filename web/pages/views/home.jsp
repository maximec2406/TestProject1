
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
                <p>Главная</p>
            </div>
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="/home" class="breadcrumb">Главная</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="abr-content-body">
        <div class="row">
            <div class="col s6 m4">
                <div class="card small blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title">Книги</span>
                        <i class="large material-icons">book</i>
                    </div>
                    <div class="card-action">
                        <a href="/book">Книги</a>
                    </div>
                </div>
            </div>
            <div class="col s6 m4">
                <div class="card small blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title">Авторы</span>
                        <i class="large material-icons">assignment_ind</i>
                    </div>
                    <div class="card-action">
                        <a href="/author">Авторы</a>
                    </div>
                </div>
            </div>
            <div class="col s6 m4">
                <div class="card small blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title">Друзья</span>
                        <i class="large material-icons">group</i>
                    </div>
                    <div class="card-action">
                        <a href="/friends">Друзья</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col s6 m4">
                <div class="card small blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title">Рекомендации</span>
                        <i class="large material-icons">record_voice_over</i>
                    </div>
                    <div class="card-action">
                        <a href="/recomends">Рекомендации</a>
                    </div>
                </div>
            </div>
            <div class="col s6 m4">
                <div class="card small blue-grey darken-1">
                    <div class="card-content white-text">
                        <span class="card-title">Поиск</span>
                        <i class="large material-icons">search</i>
                    </div>
                    <div class="card-action">
                        <a href="/search">Поиск</a>
                    </div>
                </div>
            </div>
            <c:if test="${ROLE=='ADMIN'}">
                <div class="col s6 m4">
                    <div class="card small blue-grey darken-1">
                        <div class="card-content white-text">
                            <span class="card-title">Администрирование</span>
                            <i class="large material-icons">flash_on</i>
                        </div>
                        <div class="card-action">
                            <a href="/admin/admin">Администрирование</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
