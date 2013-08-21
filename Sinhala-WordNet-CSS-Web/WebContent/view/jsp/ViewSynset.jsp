<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Synset</title>
<link rel="stylesheet" type="text/css" href="theme/css/showSynstyle.css">
<link rel="shortcut icon" href="images/wordnet1.jpg" />
<script type="text/javascript" src="theme/js/jscode.js"></script>

<script language="javascript" type="text/javascript"
	src="theme/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript"
	src="theme/js/arbor.js"></script>
<script language="javascript" type="text/javascript"
	src="theme/js/graphics.js"></script>
<script language="javascript" type="text/javascript"
	src="theme/js/renderer.js"></script>
</head>
<body>
	<div id="warp">
		<div class="disngraph">
			<div class="discript">
				<div class="header">
					<h1>${synset.getWordsAsString()}</h1>
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
				<canvas id="viewport" width="400" height="400"> </canvas>
				<script language="javascript" type="text/javascript">
					var sys = arbor.ParticleSystem({repulsion:500, stiffness:1000, friction:10000, gravity:false, fps:55, dt:0.02, precision:0.6});
					sys.parameters({
						gravity : true
					});
					sys.renderer = Renderer("#viewport");
					var data = {
						nodes : {
							${synset.getOffset()} : {
								'color' : '#FF0033',
								'label' : '${synset.getWordsAsString()}'
							}
							
							<c:choose>
							<c:when test="${hyponymsList.size() > 0}">
							<c:forEach var="hyponym" items="${hyponymsList}">
							,
							${hyponym[0].getOffset()} : {
								'color' : '#0066FF',
								'label' : '${hyponym[0].getWordsAsString()}'
							}
							</c:forEach>
							
							</c:when>
							</c:choose>
							
							<c:choose>
							<c:when test="${parentsList.size() > 0}">
							<c:forEach var="hypernym" items="${parentsList}">
							,
							${hypernym[0].getOffset()} : {
								'color' : '#14BA02',
								'label' : '${hypernym[0].getWordsAsString()}'
							}
							</c:forEach>
							
							</c:when>
							</c:choose>
							
						},
						

						edges : {
			
							${synset.getOffset()} : {
								<c:choose>
								<c:when test="${hyponymsList.size() > 0}">
								<c:forEach var="hyponym" items="${hyponymsList}">
								${hyponym[0].getOffset()} : {},
								</c:forEach>
								</c:when>
								</c:choose>
								<c:choose>
								<c:when test="${parentsList.size() > 0}">
								<c:forEach var="parent" items="${parentsList}">
								${parent[0].getOffset()} : {},
								</c:forEach>
								</c:when>
								</c:choose>
							}
						}

					};
					sys.graft(data);
				</script>
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