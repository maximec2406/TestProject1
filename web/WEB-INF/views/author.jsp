<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: maksimovich_y
  Date: 05.08.2019
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Add new Author</title>
</head>
<body>
    <form:form method="post"
               action="/manageAuthor"
               enctype="application/x-www-form-urlencoded"
               modelAttribute="authorModel"
               >
        <table>
            <tr>
                <td>FirstName:</td>
                <td><form:input path="first_name" placeholder="Имя"/></td>
                <td><form:errors path="first_name" cssClass="error" /></td>
            </tr>
            <tr>
                <td>LastName:</td>
                <td><form:input path="last_name" placeholder="Фамилия"/></td>
                <td><form:errors path="last_name" cssClass="error" /></td>
            </tr>
<%--            <tr>--%>
<%--                <td>Birthday:</td>--%>
<%--                <td><form:input type="date" path="birthday" placeholder="День рождения"/></td>--%>
<%--                <td><form:errors  path="birthday" cssClass="error" /></td>--%>
<%--            </tr>--%>
            <tr>
                <td colspan="3">
<%--                    <input type="submit" value="Register">--%>
                    <input type="submit">
                </td>
            </tr>
        </table>

    </form:form>

</body>
</html>
