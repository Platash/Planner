<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit user</title>

    <spring:url value="/resources/js/jquery-ui.css" var="uiCSS" />
    <spring:url value="/resources/css/jquery-ui-timepicker-addon.css" var="tpCSS" />
    <spring:url value="/resources/js/jquery.js" var="jsJS" />
    <spring:url value="/resources/js/jquery-ui.js" var="uiJS" />
    <spring:url value="/resources/js/jquery-ui-timepicker-addon.js" var="tpJS" />
    <spring:url value="/resources/css/w3.css" var="w3CSS" />
    <link href="${uiCSS}" rel="stylesheet" />
    <link href="${tpCSS}" rel="stylesheet" />
    <link href="${w3CSS}" rel="stylesheet" />
    <script src="${jsJS}"></script>
    <script src="${uiJS}"></script>
    <script  src="${tpJS}" ></script>


    <script>
        $(function aaa() {
            $( "#date" ).datetimepicker();
        });
        $(function bbb() {
            var startDateTextBox = $('#start');
            var endDateTextBox = $('#end');

            $.timepicker.datetimeRange(
                    startDateTextBox,
                    endDateTextBox,
                    {
                        minInterval: (1000*60*60), // 1hr
                        dateFormat: 'yy-mm-dd',
                        timeFormat: 'HH:mm:ss',
                        start: {}, // start picker options
                        end: {} // end picker options
                    }
            );
        });
    </script>
</head>
<body>

<div class="w3-container w3-green">
    <h1>Planner</h1>
</div>
<br>
<nav class="w3-sidenav w3-light-grey w3-card-8">
    <a href="newTask">New task</a>
    <a href="calendar">My calendar</a>
    <a class="w3-green" href="#">Profile</a>
    <a href="logout">Log out</a>
</nav>
<br>
<div style="margin-left:25%">


    <form:form method="POST" modelAttribute="user" onsubmit="return check_pass()">
        <table>
            <tr>
                <td><label>Login: </label> </td>
                <td><label>${user.login}<label/></td>

            </tr>
            <tr>
                <td><label>User name: </label> </td>
                <td><form:input path="name" id="name" value="${user.name}"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="password">New password: </label> </td>
                <td><form:input type="password" path="password" id="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="password">Repeat new password: </label> </td>
                <td><input type="password" id="password2"/></td>
            </tr>

            <tr>
                <td colspan="3">
                    <input class="w3-btn w3-green" type="submit" value="update" name="update"/>
                    <input class="w3-btn w3-red" type="submit" value="delete" name="delete"/>
                    <a class="w3-btn w3-green" href='calendar'>Cancel</a>
                </td>
            </tr>
        </table>
    </form:form>


</div>tables
<br>
<div class="w3-container w3-green">
    <h5>Footer</h5>
</div>
</body>
</html>

<script>
    function check_pass() {
        var pass1 = document.getElementById("password").value;
        var pass2 = document.getElementById("password2").value;
        var login = document.getElementById("login").value;
        var ok = true;
        if(login == "") {
            document.getElementById("login").style.borderColor = "#E34234";
            ok = false;
        }
        if(pass1 != pass2) {
            //alert("Passwords Do not match");
            document.getElementById("password").style.borderColor = "#E34234";
            document.getElementById("password2").style.borderColor = "#E34234";
            ok = false;
            alert("Passwords doesn't match");
        }
        else if(pass1 == ""){
            document.getElementById("password").style.borderColor = "#E34234";
            document.getElementById("password2").style.borderColor = "#E34234";
            ok = false;
            alert("Password can not be empty");
        }
        return ok;
    }
</script>