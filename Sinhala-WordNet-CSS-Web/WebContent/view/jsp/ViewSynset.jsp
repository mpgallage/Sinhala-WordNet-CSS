<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Synset</title>
<link rel="stylesheet" type="text/css" href="theme/css/showSynstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
<script type="text/javascript" src="theme/js/jscode.js"></script>

<script language="javascript" type="text/javascript">
function doubleClicked(item){
	var id = item.options[item.selectedIndex].value;
	window.location.href= 'ViewSynset?action=ViewSynset&type=<c:out value="${type}"/>&id=' + id;
}
</script>
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
		<div class="disngraph">
			<div class="discript">
				<div class="header">
					<h1>${synset.getWordsAsString()}</h1>
					<input type="button" class="button" value="Edit"
                                    onclick="window.location.href='EditSynsets?action=ShowEditSynset&type=<c:out value="${type}"/>&id=<c:out value="${synset.getOffset()}"/>'" />
				</div>
				<table>
					<tbody>

						<tr>
							<div class="dataAll">
								<td>
									<div class="data">
										<p>Synset</p>
									</div>
								</td>
								<td>
									<div class="dataDis">
										<p>${synset.getWordsAsString()}</p>
									</div>
								</td>
							</div>
						</tr>

						<tr>
							<div class="dataAll">

								<td>
									<div class="data">
										<p>Definition</p>
									</div>
								</td>
								<td>
									<div class="dataDis">
										<p>${synset.getDefinition()}</p>
									</div>
								</td>

							</div>
						</tr>
						<tr>
							<div class="dataAll">

								<td>
									<div class="data">
										<p>Example</p>
									</div>
								</td>
								<td>
									<div class="dataDis">
										<p>${synset.getExample()}</p>
									</div>
								</td>


							</div>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<%--c:forEach var="synset" items="${hyponymsList}">
					<tr>
						<td>
							<h3>
								<c:choose>
									<c:when
										test="${synset[0].getWordsAsString() == synset[1].getWordsAsString()}">
										<a href=ShowSynsets?action=ShowHyponyms&type=
											<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>>${synset[0].getWordsAsString()}(No
											sinhala words)</a>
									</c:when>
									<c:otherwise>
										<a href=ShowSynsets?action=ShowHyponyms&type=
											<c:out value="${type}"/>&id=<c:out value="${synset[0].getOffset()}"/>>${synset[0].getWordsAsString()}(${synset[1].getWordsAsString()})</a>
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
					</tr>
				</c:forEach --%>


			</div>
			<div class="graph12">
			<h3>Hypernyms</h3>
			                 <select id="list1" size="4"
                                style="width: 400px" multiple="multiple"
                                ondblclick="doubleClicked(this)">
                                    <c:forEach var="parent" items="${parentsList}">
                                        <c:choose>
                                            <c:when test="${parent[1].getWordsAsString() == parent[0].getWordsAsString()}">
                                                <option value=${parent[0].getOffset() }>${parent[0].getWordsAsString()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value=${parent[0].getOffset() }>${parent[1].getWordsAsString()}(${parent[0].getWordsAsString()})</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
            <h3>Hyponyms</h3>
                             <select id="list1" size="10"
                                style="width: 400px" multiple="multiple"
                                ondblclick="doubleClicked(this);">
                                    <c:forEach var="hyponym" items="${hyponymsList}">
                                        <c:choose>
                                            <c:when test="${hyponym[1].getWordsAsString() == hyponym[0].getWordsAsString()}">
                                                <option value=${hyponym[0].getOffset() }>${hyponym[0].getWordsAsString()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value=${hyponym[0].getOffset() }>${hyponym[1].getWordsAsString()}(${hyponym[0].getWordsAsString()})</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
			</div>
		</div>
		<div class="tblDiv">
			<div class="intable" align="center">
				<div class="fullTable" align="center">
					<table border="1" class="wordTable" cellpadding='1' cellspacing='1'>
						<thead class="thead">
							<tr>
								<th>Word</th>
								<th>Antonym</th>
								<th>Root</th>
								<th>Origin Language</th>
								<th>Derivation Type</th>
							</tr>
						</thead>
						<tbody class="tbody">
							<c:forEach var="word" items="${synset.getWords()}">
								<tr>
									<td>${word.getLemma()}</td>
									<td>${word.getAntonym().getLemma()}</td>
									<td>-</td>
									<td>-</td>
									<td>-</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>

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