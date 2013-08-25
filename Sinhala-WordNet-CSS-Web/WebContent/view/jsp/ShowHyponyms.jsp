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
</head>

<body>
	<div id="warp">
	<ul id="breadcrumbs">
	   <c:forEach var="bcObject" items="${breadCrumb.breadCrumbList}" varStatus="loop">
	       <c:choose>
                <c:when test="${breadCrumb.breadCrumbList.size()-1 > loop.index}">
                    <li title="${bcObject.lemma}"><a href="${bcObject.link}">${bcObject.wordsAsCSV}</a></li>
                </c:when>
                <c:otherwise>
                    <li title="${bcObject.lemma}">${bcObject.wordsAsCSV}</li>
                </c:otherwise>
          </c:choose>
       </c:forEach>
    </ul>
        <div id="summary">
            <h1>සාරාංශය</h1>
            <div>
                <div class="word_set">
                    <div class="whitebox">
                        <h3>${enSynset.getWordsAsString()}</h3>
                    </div>
                    <div class="whitebox">
                        <table>
                            <tr>
                                <td valign="top">අර්ථය:</td>
                                <td valign="top">${enSynset.getDefinition()}</td>
                            </tr>
                            <tr>
                                <td valign="top">උදාහරණ:</td>
                                <td valign="top">${enSynset.getExample()}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
        <div id="gap"></div>
		<div id="menu">
			<table>
				<col>
				<c:choose>
					<c:when test="${synsetList.size()>0}">
						<c:forEach var="synset" items="${synsetList}" varStatus="loop">
							<tr>
								<td>
									<h3>
									<c:choose>
                                            <c:when test="${nextLevelList[loop.index] == true}">
												<c:choose>
													<c:when
														test="${synset[0].getWordsAsString() == synset[1].getWordsAsString()}">
														<a href=ShowSynsets?action=ShowHyponyms&type=<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>>${synset[0].getWordsAsString()}(No sinhala words)</a>
													</c:when>
													<c:otherwise>
														<a href=ShowSynsets?action=ShowHyponyms&type=<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>>${synset[0].getWordsAsString()}(${synset[1].getWordsAsString()})</a>
													</c:otherwise>
												</c:choose>
										    </c:when>
										    <c:otherwise>
										        <c:choose>
                                                    <c:when
                                                        test="${synset[0].getWordsAsString() == synset[1].getWordsAsString()}">
                                                        <a>${synset[0].getWordsAsString()}(No sinhala words)</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a>${synset[0].getWordsAsString()}(${synset[1].getWordsAsString()})</a>
                                                    </c:otherwise>
                                                </c:choose>
										    </c:otherwise>
								    </c:choose>
									</h3>
								</td>
								<td><input type="button" class="button" value="View"
									onclick="window.location.href='ViewSynset?action=ViewSynset&type=<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>'" />
								</td>
								<td><input type="button" class="button" value="Edit"
									onclick="window.location.href='EditSynsets?action=ShowEditSynset&type=<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>'" />
								</td>
								<td><input type="button" class="button" value="Relations"
                                    onclick="window.location.href='EditRelationship?action=ShowEditRelationship&type=<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>'" />
                                </td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td>
								<h3>This synset has no hyponyms. Please go back.</h3>
							</td>
							<td><input type="button" class="button" value="Back"
								onclick="window.location.href='ShowSynsets?action=ShowHyponyms&type=<c:out value="${type}"/>&id=<c:out value="${parent}"/>'" />
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
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