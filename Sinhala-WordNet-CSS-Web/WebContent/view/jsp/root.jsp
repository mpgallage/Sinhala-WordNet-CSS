<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Categories</title>
<link rel="stylesheet" type="text/css" href="theme/css/wordnetstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
<script type="text/javascript" src="theme/scripts/autoBredcrums.js"></script>

</head>
<body>
	<div class="top_div">
		<table>
			<tr>
				<td>
					<h3>Sinhala WordNet CrowdSourcing System</h3>
				</td>
				<sec:authorize access="isAuthenticated()">
					<td>You are logged in as <b><sec:authentication
							property="principal.username" /></b>
				</sec:authorize>
				<td><a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
				</td>
			</tr>
		</table>
	</div>

	<div id="warp">
		<div id="menu">
			<table>
				<col width="240">
				<tr>
					<td><h3>
							<a href=ShowSynsets?action=ShowHyponyms&type=noun>නාම පද
								(Nouns)</a>
						</h3></td>
					<td><input type="button" class="button" value="Show Children"
						onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=noun'" /></td>
				</tr>
				<tr>
					<td><h3>
							<a href=ShowSynsets?action=ShowHyponyms&type=verb>ක්‍රියා පද
								(Verbs)</a>
						</h3></td>
					<td><input type="button" class="button" value="Show Children"
						onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=verb'" /></td>
				</tr>
				<tr>
					<td><h3>
							<a href=ShowSynsets?action=ShowHyponyms&type=adj>නාම විශේෂණ
								(Adjectives)</a>
						</h3></td>
					<td><input type="button" class="button" value="Show Children"
						onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=adj'" /></td>
				</tr>
				<tr>
					<td><h3>
							<a href=ShowSynsets?action=ShowHyponyms&type=adv>ක්‍රියා
								විශේෂණ (Adverbs)</a>
						</h3></td>
					<td><input type="button" class="button" value="Show Children"
						onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=adv'" /></td>
				</tr>
			</table>
		</div>
		<div class="footer">
			<div class="links">
				<p class="lintitle1">
					© සියලුම හිමිකම් ඇවිරිණි. <a href="http://www.uom.lk/"
						target="_new" class="cse">සිංහල Wordnet ව්‍යාපෘතිය.</a>
				</p>
			</div>
		</div>
	</div>

</body>
</html>
>
