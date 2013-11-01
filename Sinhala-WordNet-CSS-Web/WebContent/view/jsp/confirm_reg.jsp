<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message</title>
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
				<td><a href="<c:url value="login.jsp"/>">Login</a></td>
			</tr>
		</table>
	</div>

	<div id="warp" style="padding-left: 2%">
		<h4 style="color: #FF0000;">${error}</h4>
		<h4 style="color: #00FF00;">${success}</h4>
	</div>

</body>
</html>