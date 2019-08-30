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
</head>
<body>
<c:choose>
    <c:when test="${regim == 'List'}">
        <p>List</p>
        <a href="/createAuthor"><button>Добавить Автора</button></a>
        <c:forEach items="${authors}" var="author" >
            <p>
                <span>${author.first_name}</span><span>${author.author.last_name}</span>
            </p>
        </c:forEach>
    </c:when>
    <c:when test="${regim == 'Create'}">
        <p>Create</p>
        <a href="/books"><button>Список Авторов</button></a>
        <form:form method="post"
                   action="/createAuthor"
                   enctype="application/x-www-form-urlencoded"
                   modelAttribute="authorModel"
        >
            <table>
                <tr>
                    <td>Имя:</td>
                    <td><form:input path="first_name" placeholder="Имя"/></td>
                    <td><form:errors path="first_name" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Фамилия:</td>
                    <td><form:input path="last_name" placeholder="Фамилия"/></td>
                    <td><form:errors path="last_name" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Отчество:</td>
                    <td><form:input path="patronymic" placeholder="Отчество"/></td>
                    <td><form:errors path="patronymic" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>День рождения:</td>
                    <td><form:input type="date" path="birthday" placeholder="День рождения"/></td>
                    <td><form:errors  path="birthday" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>День смерти:</td>
                    <td><form:input type="date" path="deathday" placeholder="День рождения"/></td>
                    <td><form:errors  path="deathday" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Об Авторе:</td>
                    <td><form:input path="about"/></td>
                    <td><form:errors path="about" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Фото:</td>
                    <td><form:input path="photo" placeholder="http:/"/></td>
                    <td><form:errors path="photo" cssClass="error" /></td>
                </tr>
                <tr>
                    <td>Страна:</td>
                    <td>
                        <form:select path="country" itemValue="${authorModel.country}">
                            <form:options type="text" items="${EnumCountry}" />
                        </form:select>
                    </td>
                    <td><form:errors path="country" cssClass="error" /></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit">
                    </td>
                </tr>
            </table>
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
