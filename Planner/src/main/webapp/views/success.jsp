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
    <div>
        <nav class="w3-sidenav w3-light- w3-card-8grey">
            <a href="newTask">New task</a>
            <a href="calendar" class="w3-green">My calendar</a>
            <a href="editUser">Profile</a>
            <a href="logout">Log out</a>
        </nav>
    </div>
    <br>
    <div style="margin-left:25%; margin-right:5%"> Success. You will be redirected to the calendar page in <label id="countDown">3</label>
        sec.</div>
    <br>
    <div class="w3-container w3-green">
        <h5>Footer</h5>
    </div>

</body>

</html>
<script>
    var count = 3;
    setInterval(function(){
        count--;
        document.getElementById('countDown').innerHTML = count;
        if (count == 0) {
            window.location = 'calendar';
        }
    },1000);
</script>