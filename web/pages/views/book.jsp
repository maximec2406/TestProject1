<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="securety" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<p>Книги</p>
<c:choose>
    <c:when test="${regime == 'List'}">
        <p>List</p>
        <a href="/createBook"><button>Добавить книгу</button></a>
        <c:forEach items="${books}" var="book" >
            <p>
                <span>${book.name}</span><span>${book.author.last_name}</span>
            </p>
        </c:forEach>
    </c:when>
    <c:when test="${regime == 'Create'}">
        <p>Create</p>
        <a href="/book"><button>Список книг</button></a>
        <form:form enctype="application/x-www-form-urlencoded"
                   action="/createBook"
                   method="post"
                   modelAttribute="bookModel">
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
            </p>
            <p>
                <form:errors path="errorMessage" type="text"/>
            </p>
            <p>
                <input type="submit" value="Create">
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