<jsp:useBean id="bean" class="ru.abr.dit.Beans.TestBean" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>It`s user</p>
<p>Name: ${bean.getName()}</p>
<p>String: ${bean.toString()}</p>
<p>User: ${users[0].getLogin()}</p>
<c:forEach var="user" items="${users}">
    <p>${user.login}</p>
</c:forEach>