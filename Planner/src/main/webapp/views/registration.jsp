<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Registration Form</h2>
 
	<form:form method="POST" modelAttribute="user" onsubmit="return check_pass()">
		<table>
			<tr>
				<td><label for="login">login: </label> </td>
				<td><form:input path="login" id="login"/></td>
				<td><form:errors path="login" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="password">Password: </label> </td>
				<td><form:input type="password" path="password" id="password"/></td>
				<td><form:errors path="password" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="password">Repeat password: </label> </td>
				<td><input type="password" id="password2"/></td>
			</tr>
            <tr>
                <td><label for="name">name: </label> </td>
                <td><form:input path="name" id="name"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>

			<tr>
				<td colspan="3">
					<input type="submit" value="register"/>

				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>

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