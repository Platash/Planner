<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users list</title>

</head>


<body>
	<h2>Users</h2>

    <table>
        <tr>
            <th>id</th>
            <th>login</th>
            <th>name</th>
            <th>acc creation date</th>

        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.creationDate}</td>

            </tr>
        </c:forEach>
    </table>

</body>
</html>