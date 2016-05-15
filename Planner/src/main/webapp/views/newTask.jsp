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
            //$( "#date" ).datepicker();

            //$( "#date" ).bootstrapMaterialDatePicker({ weekStart : 0, time: false });
        });


        $(function bbb() {
            var startDateTextBox = $('#start');
            var endDateTextBox = $('#end');

            $.timepicker.datetimeRange(
                    startDateTextBox,
                    endDateTextBox,
                    {
                        minInterval: (1000*60*60), // 1hr
                        dateFormat: 'dd M yy',
                        timeFormat: 'HH:mm',
                        start: {}, // start picker options
                        end: {} // end picker options
                    }
            );
        });


    </script>
</head>
<body>

<form:form method="POST" modelAttribute="task">


    <table>
        <tr>
            <td><label>date: </label> </td>
            <td><form:input path="startDate" id="start"/></td>
            <td><form:errors path="startDate" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>date: </label> </td>
            <td><form:input path="endDate" id="end"/></td>
            <td><form:errors path="endDate" cssClass="error"/></td>
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