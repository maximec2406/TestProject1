<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="securety" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Books</title>
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
                    <span>Книги</span>
                </div>
            </div>
            <nav>
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="/home" class="breadcrumb">Главная</a>
                        <a href="/#" class="breadcrumb">Книги</a>
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
                        <h3>Список Книг</h3>
                    </div>
                    <div>
                        <a href="/createBook" class="waves-effect waves-light btn">Добавить книгу</a>
                    </div>
                </div>
                <div class="abr-list-body">
                    <div class="collection">
                        <c:forEach items="${books}" var="book" >
                            <a href="http://localhost:8585/editBook?id=${book.id}" class="collection-item">${book.name}</a>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:when test="${regime == 'Create'}">
                <div class="abr-list-head">
                    <div >
                        <h3>Добавить Книгу</h3>
                    </div>
                    <div>
                        <a href="/book" class="waves-effect waves-light btn">Список книг</a>
                    </div>
                </div>
                <div class="abr-list-body">

                    <form:form enctype="application/x-www-form-urlencoded"
                               action="/createBook"
                               method="post"
                               modelAttribute="bookModel">
                        <form:hidden path="id"/>
                        <p>
                            <label for="name">Наименование книги</label>
                            <form:input path="name" type="text"/>
                            <form:errors path="name" />
                        </p>
                        <p>
                            <label for="discription">Описание</label>
                            <form:input path="discription" type="text" />
                            <form:errors path="discription"/>
                        </p>
                        <p>
                            <label for="year">Год издания</label>
                            <form:input path="year" type="text" />
                            <form:errors path="discription"/>
                        </p>
                        <p>
                            <label for="genre">Жанр</label>
                            <form:select path="genre" itemValue="${newBook.genre}">
                                <form:options type="text" items="${Genres}" />
                            </form:select>
                            <form:errors path="genre"/>
                        </p>
                        <p>
                            <label for="original_lang">Язык оригинала</label>
                            <form:select path="original_lang" itemValue="${newBook.original_lang}">
                                <form:options type="text" items="${Languages}" />
                            </form:select>
                            <form:errors path="original_lang"/>
                        </p>
                        <p>
                            <label for="author">Автор</label>
                            <form:select path="author" itemValue="${newBook.author}">
                                <form:options type="text" items="${Authors}" />
                            </form:select>
                            <form:errors path="author"/>
                            <form:hidden path="authorId"/>
                        </p>
                        <p>
                            <form:errors path="errorMessage" type="text"/>
                        </p>
                        <c:if test="${regime == 'Edit'}">
                            <c:if test="${saveResult != null}">
                                <p>${saveResult}</p>
                            </c:if>
                        </c:if>
                        <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/saveBook" >Сохранить</button>
                        <c:if test="${regime == 'Edit'}">
                            <button class="btn waves-effect waves-light" type="submit" name="action" formaction="/deleteBook">Удалить</button>
                        </c:if>
                        <div hidden>
                            <securety:csrfInput/>
                        </div>
                    </form:form>
                </div>
            </c:when>
        </c:choose>
    </div>
</body>
</html>