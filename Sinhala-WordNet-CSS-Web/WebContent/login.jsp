<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				

				<td><a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
				</td>
			</tr>
		</table>
	</div>
<div id="loginpanal">

<div id="loghead"><h2 class="title">User login panel</h2></div>

 <c:if test="${not empty param.login_error}">
 <font color="#ff0000">
 Login unsuccessful.<br/>
 <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
 </font>
</c:if>
 <form action="j_spring_security_check" method="POST">
 
 <label for="username">User Name:</label></td>
 <input id="username" name="j_username" type="text" /><br>
 <label for="password">Password:</label></td>
 <input id="password" name="j_password" type="password" />
 
 <input type="submit" value="Log In" />
 </form>
 <p>New user ? - <a href="SignUp?action=Register">Register</a> </p>
 
 </div>
</body>
</html>