<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="theme/css/wordnetstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
</head>
<body>

	<div class="top_div">
		<table>
			<tr>
				<td>
					<h3>Sinhala WordNet CrowdSourcing System</h3>
				</td>


				<td><a href="<c:url value="http://www.wordnet.lk"/>">Home</a>
				</td>
			</tr>
		</table>
	</div>

<div id="loginDiv">
<div>
<h3>Login</h3>
</div>
	<table>
		<tr>
			<td><c:if test="${not empty param.login_error}">
					<font color="#ff0000"> Login unsuccessful.<br /> <c:out
							value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
					</font>
				</c:if></td>
		</tr>
		<tr>
			<form action="j_spring_security_check" method="POST">
				<td><label for="username">User Name:</label></td>
				<td><input class="formText" id="username" name="j_username" type="text" /><br>
				</td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td><input class="formText" id="password" name="j_password" type="password" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input id="loginBtn" type="submit" value="Log In" /></td>
		</tr>
		</form>
		<tr>
			<td colspan="2">
				<p><b>
					New user ? - <a href="SignUp?action=Register">Register</a></b>
				</p>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>