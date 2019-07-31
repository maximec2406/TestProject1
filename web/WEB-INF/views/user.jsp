<jsp:useBean id="bean" class="ru.abr.dit.Beans.TestBean" scope="request"/>
<p>It`s user</p>
<p>Name: ${bean.getName()}</p>
<p>String: ${bean.toString()}</p>
<%--<p>User: ${users[0].getlogin()}</p>--%>