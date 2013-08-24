<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Synset</title>
<link rel="stylesheet" type="text/css" href="theme/css/style.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
<script type="text/javascript" src="theme/js/add_new_div.js"></script>
<script type="text/javascript" src="theme/js/expand_collapse.js"></script>
<script type="text/javascript">
	var counter = ${synset.getWords().size()};
</script>
</head>
<body>
	<div id="wrap">
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
		<div id="new_sysnset">
			<form:form method="POST" modelAttribute="synset"
				action="EditSynsets">
				<div id="add_words" class="add_new">
					<div class="section_header">
						<h2>පද සම්බන්ධතා</h2>
					</div>
					<div id="words_div">
						<c:forEach items="${synset.words}" varStatus="loop">
							<div id="word_${loop.index}" class="word_set">
								<span id="ex_span_${loop.index}"
									onclick="ExpandCollapse(this.id);"> <c:choose>
										<c:when test="${synset.getWords().size() == loop.index}">
											<img src="theme/images/collapse.png" /></span> <span
									id="show_span_${loop.index}" class="show_span">සඟවන්න</span>
								<span id="close_${loop.index}" class="close" onclick="closeDiv(this.id);"> <img src="theme/images/close.png" />
								</span>
								<div id="ex_div_${loop.index}" style="display: block;">
									</c:when>
									<c:otherwise>
										<img src="theme/images/expand.png" />
										</span>
										<span id="show_span_${loop.index}" class="show_span">පෙන්වන්න</span>
										<span id="close_${loop.index}" class="close" onclick="closeDiv(this.id);"> <img src="theme/images/close.png" />
										</span>
										<div id="ex_div_${loop.index}" style="display: none;">
									</c:otherwise>
									</c:choose>
									<table class="word_table">
										<tbody>

											<tr>
												<td><label>සිංහල පදය</label></td>
												<td><form:input class="lemmaval"
														path="words[${loop.index}].lemma"
														type="text/html; charset=UTF-8" maxlength="255" size="22" /></td>
											</tr>

											<tr>
												<td><label>ප්‍රකෘතිය</label></td>
												<td><form:input path="words[${loop.index}].root.lemma"
														type="text" maxlength="255" size="22" /></td>
											</tr>
											<tr>
												<td><label>මූල භාෂාව</label></td>
												<td><form:select
														path="words[${loop.index}].origin.lemma">
														<form:option value="නොදනී">නොදනී</form:option>
														<form:option value="හින්දි">හින්දි</form:option>
														<form:option value="දෙමළ">දෙමළ</form:option>
														<form:option value="ඉංග්‍රීසි">ඉංග්‍රීසි</form:option>
														<form:option value="පෘතුග්‍රීසි">පෘතුග්‍රීසි</form:option>
														<form:option value="ලංදේසි">ලංදේසි</form:option>
													</form:select></td>
											</tr>
											<tr>
												<td><label>මූල භාෂා වර්ගය</label></td>
												<td><form:radiobutton
														path="words[${loop.index}].derivationType.lemma"
														value="තත්සම" />තත්සම<br> <form:radiobutton
														path="words[${loop.index}].derivationType.lemma"
														value="තත්භව" />තත්භව<br>
											</tr>
											<tr>
												<td><label>භාවිතය</label></td>
												<td><form:radiobutton
														path="words[${loop.index}].usage.lemma" value="වාචික" />වාචික<br>
													<form:radiobutton path="words[${loop.index}].usage.lemma"
														value="ලිඛිත" />ලිඛිත<br>
											</tr>
											<tr>
												<td><label>විරුද්ධ පදය</label></td>
												<td><form:input
														path="words[${loop.index}].antonym.lemma" type="text"
														maxlength="255" size="22" /></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="button_div">
						<input type="button" value="නව පදයක් යෙදීම" id="btn_add_word"
							class="button" onclick="copyDiv()" />
					</div>

					<div class="section_header">
						<h2>පද කුලකයේ සම්බන්ධතා</h2>
					</div>
					<div class="word_set">
						<table class="word_table">
							<tbody>
								<tr>
									<td><form:hidden path="offset" /></td>
								</tr>
								<tr>
									<td><label>සිංහල අර්ථය :</label></td>
									<td><form:textarea path="definition" rows="5" cols="30" /></td>
								</tr>
								<tr>
									<td><label>සිංහල උදාහරණ :</label></td>
									<td><form:textarea path="example" rows="5" cols="30" /></td>
								</tr>
								<tr>
									<td><label>ලිංග භේදය</label></td>
									<td><form:radiobutton path="gender" value="පුරුෂ" />පුරුෂ<br>
										<form:radiobutton path="gender" value="ස්ත්‍රී" />ස්ත්‍රී<br>
										<form:radiobutton path="gender" value="නොසලකා හරින්න" />නොසලකා
										හරින්න</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="button_div">
						<input type="submit" value="යොමන්න" id="btn_add_synset"
							class="button" style="float: right" />
					</div>
				</div>
			</form:form>
		</div>
		<div id="synset_relation" class="add_new">
			<div>
				<div class="word_set">
					<div class="whitebox">
						<h1>${enSynset.getWordsAsString()}</h1>
					</div>
					<div class="whitebox">
						<table>
							<tr>
								<td valign="top">ඉංග්‍රීසි අර්ථය:</td>
								<td valign="top">${enSynset.getDefinition()}</td>
							</tr>
							<tr>
								<td valign="top">ඉංග්‍රීසි උදාහරණ:</td>
								<td valign="top">${enSynset.getExample()}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="word_set">
				<center>
					<p>වචනවල පොදු අරුත්</p>
					<table width="238">
						<tr>
							<td width="125" valign="top"><p>${enSynset.getWordsAsString()}
									:</p></td>
							<td width="101" valign="top"><select id="list1" size="4"
								style="width: 100px" multiple="multiple"
								ondblclick="add2list(&#39;list1&#39;,&#39;synsetlist&#39;);">
									<c:forEach var="meaning" items="${intersection}">
										<option>${meaning}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</center>
			</div>
			<div class="word_set">
				<center>
					<p>වචනවල අරුත්</p>
				</center>
				<table>
					<tr>
						<c:forEach begin="0" end="${enWordList.size()-1}" varStatus="loop">
							<td width="" valign="top"><p>${enWordList.get(loop.index)}
									:</p></td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach begin="0" end="${enWordList.size()-1}" varStatus="loop">
							<td width="" valign="top"><select id="list2" size="10"
								style="width: 120px" multiple="multiple">
									<c:forEach var="meaning"
										items="${meaningsList.get(loop.index)}">
										<option>${meaning}</option>
									</c:forEach>
							</select></td>
						</c:forEach>
					</tr>
				</table>
			</div>
		</div>
	<div class="footer">
		<div class="links">
			<p class="lintitle1">
				© සියලුම හිමිකම් ඇවිරිණි. <a href="http://www.uom.lk/" target="_new"
					class="cse">සිංහල Wordnet ව්‍යාපෘතිය.</a>
			</p>
		</div>
	</div>
	</div>
</body>
</html>
