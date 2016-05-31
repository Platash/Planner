
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<html>
<head>
    <title>Calendar</title>
    <spring:url value="/resources/css/fullcalendar.css" var="fcCSS" />
    <spring:url value="/resources/js/jquery-ui.css" var="uiCSS" />
    <spring:url value="/resources/css/jquery-ui-timepicker-addon.css" var="tpCSS" />
    <spring:url value="/resources/js/jquery.js" var="jsJS" />
    <spring:url value="/resources/js/moment.js" var="mmJS" />
    <spring:url value="/resources/js/jquery-ui.js" var="uiJS" />
    <spring:url value="/resources/js/jquery-ui-timepicker-addon.js" var="tpJS" />
    <spring:url value="/resources/js/fullcalendar.js" var="fcJS" />
    <link href="${uiCSS}" rel="stylesheet" />
    <link href="${tpCSS}" rel="stylesheet" />
    <link href="${fcCSS}" rel="stylesheet" />

    <script src="${jsJS}"></script>
    <script src="${mmJS}"></script>
    <script src="${uiJS}"></script>
    <script  src="${tpJS}" ></script>
    <script  src="${fcJS}" ></script>
</head>
<body>
    <script>
        $(document).ready(function() {

            // page is now ready, initialize the calendar...

            $('#calendar').fullCalendar({
                height: 450,
                events: {
                    url: '/cal/json',

                    contentType : "application/json",
                    dataType : 'json',
                    data: {


                    },
                    error: function() {
                        alert('there was an error while fetching events!');
                    },
                    color: 'yellow',   // a non-ajax option
                    textColor: 'black' // a non-ajax option
                }
            })

        });
    </script>
    <div id='calendar'></div>

</body>
</html>
