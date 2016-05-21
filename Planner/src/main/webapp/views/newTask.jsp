<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>New task</title>

    <spring:url value="/resources/js/jquery-ui.css" var="uiCSS" />
    <spring:url value="/resources/css/jquery-ui-timepicker-addon.css" var="tpCSS" />
    <spring:url value="/resources/js/jquery.js" var="jsJS" />
    <spring:url value="/resources/js/jquery-ui.js" var="uiJS" />
    <spring:url value="/resources/js/jquery-ui-timepicker-addon.js" var="tpJS" />
    <link href="${uiCSS}" rel="stylesheet" />
    <link href="${tpCSS}" rel="stylesheet" />
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

<form:form method="POST" modelAttribute="taskForm">


    <table>
        <tr>
            <td><label>Name: </label> </td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>Start date: </label> </td>
            <td><form:input path="startDate" id="start"/></td>
            <td><form:errors path="startDate" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>End date: </label> </td>
            <td><form:input path="endDate" id="end"/></td>
            <td><form:errors path="endDate" cssClass="error"/></td>
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
                <input type="submit" value="submit"/>

            </td>
        </tr>
    </table>
</form:form>

</body>
</html>