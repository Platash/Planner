<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit task</title>

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
    <a class="w3-green" href="newTask">New task</a>
    <a href="calendar">My calendar</a>
    <a href="#">Profile</a>
    <a href="logout">Log out</a>
</nav>
<br>
<div style="margin-left:25%">
    <form:form method="POST" modelAttribute="taskData" onsubmit="return validateFields()">
        <table>
            <tr>
                <td><label>Name: </label> </td>
                <td><form:input path="title" id="title" value="${taskData.title}"/></td>
                <td><form:errors path="title" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label>Start date: </label> </td>
                <td><form:input path="start" id="start" value="${taskData.start}"/></td>
                <td><form:errors path="start" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label>End date: </label> </td>
                <td><form:input path="end" id="end" value="${taskData.end}"/></td>
                <td><form:errors path="end" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label>Location: </label> </td>
                <td><form:input path="location" id="location"/></td>
                <td><form:errors path="location" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label>Description: </label> </td>
                <td><form:input path="description" id="description"/></td>
                <td><form:errors path="description" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label>Tags separated by #: </label> </td>
                <td><form:input path="tags" id="tags"/></td>
                <td><form:errors path="tags" cssClass="error"/></td>
            </tr>

            <tr>
                <td colspan="3">
                    <input class="w3-btn w3-green" type="submit" value="submit" name="update"/>
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
    function validateFields() {
        var start = document.getElementById("start").value;
        var end  = document.getElementById("end").value;

        var ok = true;
        if(start == "") {
            document.getElementById("start").style.borderColor = "#E34234";
            ok = false;
        }
        if(end == "") {
            document.getElementById("end").style.borderColor = "#E34234";
            ok = false;
        }

        return ok;
    }
</script>