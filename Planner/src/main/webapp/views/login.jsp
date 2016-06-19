<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<html>
<head>
    <title>Calendar</title>
    <spring:url value="/resources/js/jquery-ui.css" var="uiCSS" />
    <spring:url value="/resources/css/jquery-ui-timepicker-addon.css" var="tpCSS" />
    <spring:url value="/resources/js/jquery.js" var="jsJS" />
    <spring:url value="/resources/js/moment.js" var="mmJS" />
    <spring:url value="/resources/js/jquery-ui.js" var="uiJS" />
    <spring:url value="/resources/css/w3.css" var="w3CSS" />

    <link href="${uiCSS}" rel="stylesheet" />
    <link href="${tpCSS}" rel="stylesheet" />
    <link href="${w3CSS}" rel="stylesheet" />

    <script src="${jsJS}"></script>
    <script src="${mmJS}"></script>
    <script src="${uiJS}"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Success</title>
</head>
<body>
<div class="w3-container w3-green">
    <h1>Planner</h1>
</div>
<br>
<h2>Log in please:</h2>

<form:form method="POST" modelAttribute="user">
    <table>
        <tr>
            <td><label for="login">login: </label> </td>
            <td><form:input path="login" id="login"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="password">password: </label> </td>
            <td><form:input type="password" path="password" id="password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">
                <input class="w3-btn w3-green" type="submit" value="Log in"/>
            </td>
        </tr>

    </table>
</form:form>
<br/>
<div>
    <div>Or create an account</div>
    <a class="w3-btn w3-green" href='registration'>Create account</a>

</div>
<br/>

</body>
</html>

