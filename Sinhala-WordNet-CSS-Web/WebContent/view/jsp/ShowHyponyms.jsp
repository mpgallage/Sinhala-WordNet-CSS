<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Hyponyms</title>
<link rel="stylesheet" type="text/css" href="theme/css/wordnetstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
<script type="text/javascript" src="theme/scripts/autoBredcrums.js"></script>
</head>

<body>
	<div id="warp">
		<div id="menu">
			<table>
				<col width="240">
				<c:choose>
					<c:when test="${synsetList.size()>0}">
						<c:forEach var="synset" items="${synsetList}">
							<tr>
								<td>
									<h3>
										<a href=ShowSynsets?action=ShowHyponyms&type=<c:out value="${type}"/>&id=<c:out value="${synset.getOffset()}"/>>${synset.getWordList()}</a>
									</h3>
								</td>
								<td><input type="button" class="button"
									value="View"
									onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=<c:out value="${type}"/>&id=<c:out value="${synset.getOffset()}"/>'" />
								</td>
								<td><input type="button" class="button" value="Edit"
									onclick="window.location.href='EditSynsets?action=ShowEditSynset&type=<c:out value="${type}"/>&id=<c:out value="${synset.getOffset()}"/>'" />
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td>
								<h3>This synset has no hyponyms. Please go back.</h3>
							</td>
							<td>
				                <input type="button" class="button" value="Back"
				                        onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=<c:out value="${type}"/>&id=<c:out value="${parent}"/>'" />
				            </td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div id="gap"></div>
		<div id="summary">
			<h3>Summary</h3>
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