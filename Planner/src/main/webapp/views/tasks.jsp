<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users list</title>

</head>


<body>
<h2>Tasks </h2>

<table>
    <tr>
        <th>id</th>
        <th>start</th>
        <th>end</th>
        <th>location</th>
        <th>description</th>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.start}</td>
            <td>${task.end}</td>
            <td>${task.location}</td>
            <td>${task.description}</td>

        </tr>
    </c:forEach>
</table>

</body>
</html>