<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
 Username: <sec:authentication property="principal.username" />
 Role: <sec:authentication property="principal.authorities"/>
 </sec:authorize>
<P>  The time on the server is ${serverTime}. </P>
<p>To see the contents click <a href="ShowSynsets?action=ShowRoot">here</a> </p>
<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
</body>
</html>