<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="wrap">
	<p>${error}</p>
		<form:form method="POST" modelAttribute="user" action="SignUp">

			<table class="word_table">
				<tbody>

					<tr>
						<td><label>First Name</label></td>
						<td><form:input class="fName" path="firstName"
								type="text/html; charset=UTF-8" maxlength="255" size="22" /></td>
					</tr>

					<tr>
						<td><label>Last Name</label></td>
						<td><form:input path="lastName" type="text" maxlength="255"
								size="22" /></td>
					</tr>
					<tr>
						<td><label>User Name</label></td>
						<td><form:input class="uname" path="username"
								type="text/html; charset=UTF-8" maxlength="255" size="22" /></td>
					</tr>

					<tr>
						<td><label>Password</label></td>
						<td><form:input path="password" type="password"
								maxlength="255" size="22" /></td>
					</tr>
					<tr>
						<td><label>E-mail</label></td>
						<td><form:input path="email" type="text" maxlength="255"
								size="22" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="යොමන්න" id="btn_add_synset"
							class="button" style="float: right" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>