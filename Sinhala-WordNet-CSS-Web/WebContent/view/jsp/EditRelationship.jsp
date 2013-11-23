<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Synset Relations</title>
<link rel="stylesheet" type="text/css" href="theme/css/showSynstyle.css">
<link rel="shortcut icon" href="images/wordnet1.jpg" />


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/tag-it.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css">
<link href="theme/css/jquery.tagit.css" rel="stylesheet" type="text/css">


</head>
<body>

	<div class="top_div">
		<table>
			<tr>
				<td>
					<h3>Sinhala WordNet CrowdSourcing System</h3>
				</td>
				<sec:authorize access="isAuthenticated()">
					<td>You are logged in as <b><sec:authentication property="principal.username" /></b>
				</sec:authorize>
				<td><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></td>
			</tr>
		</table>
	</div>
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
			<div class="discript_wide">
				<div class="header">
					<h1>${synset.getWordsAsString()}</h1>
				</div>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;Definition: ${synset.getDefinition()}</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;Example: ${synset.getExample()}</p>
			</div>
			<div class="discript_wide">
				<script type="text/javascript">
					hypernymJsonArray = [];
					hyponymJsonArray = [];
					memberHolonymJsonArray = [];
					substanceHolonymJsonArray = [];
					partHolonymJsonArray = [];
					memberMeronymJsonArray = [];
					substanceMeronymJsonArray = [];
					partMeronymJsonArray = [];
					attributeJsonArray = [];
					troponymJsonArray = [];
					entailmentJsonArray = [];
					causeJsonArray = [];
					alsoseeJsonArray = [];
					similarJsonArray = [];
					relationaladjJsonArray = [];
					derivedfromJsonArray = [];

					$(function() {

						// singleFieldTags2 is an INPUT element, rather than a UL as in the other 
						// examples, so it automatically defaults to singleField.
						$('#hypernym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#hypernym').tagit("tagLabel", tag);
								hypernymJsonArray.push(item);
								console.log(hypernymJsonArray);
								document.getElementById("hypernymTags").value = JSON.stringify(hypernymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#hypernym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(hypernymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								hypernymJsonArray.splice(removeIndex, 1);
								console.log(hypernymJsonArray);
								document.getElementById("hypernymTags").value = JSON.stringify(hypernymJsonArray);

							}

						});

						$('#hyponym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#hyponym').tagit("tagLabel", tag);
								hyponymJsonArray.push(item);
								console.log(hyponymJsonArray);
								document.getElementById("hyponymTags").value = JSON.stringify(hyponymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#hyponym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(hyponymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								hyponymJsonArray.splice(removeIndex, 1);
								console.log(hyponymJsonArray);
								document.getElementById("hyponymTags").value = JSON.stringify(hyponymJsonArray);

							}

						});

						$('#memberHolonym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#memberHolonym').tagit("tagLabel", tag);
								memberHolonymJsonArray.push(item);
								console.log(memberHolonymJsonArray);
								document.getElementById("memberHolonymTags").value = JSON.stringify(memberHolonymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#memberHolonym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(memberHolonymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								memberHolonymJsonArray.splice(removeIndex, 1);
								console.log(memberHolonymJsonArray);
								document.getElementById("memberHolonymTags").value = JSON.stringify(memberHolonymJsonArray);

							}
						});

						$('#substanceHolonym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#substanceHolonym').tagit("tagLabel", tag);
								substanceHolonymJsonArray.push(item);
								console.log(substanceHolonymJsonArray);
								document.getElementById("substanceHolonymTags").value = JSON.stringify(substanceHolonymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#substanceHolonym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(substanceHolonymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								substanceHolonymJsonArray.splice(removeIndex, 1);
								console.log(substanceHolonymJsonArray);
								document.getElementById("substanceHolonymTags").value = JSON.stringify(substanceHolonymJsonArray);

							}
						});

						$('#partHolonym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#partHolonym').tagit("tagLabel", tag);
								partHolonymJsonArray.push(item);
								console.log(partHolonymJsonArray);
								document.getElementById("partHolonymTags").value = JSON.stringify(partHolonymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#partHolonym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(partHolonymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								partHolonymJsonArray.splice(removeIndex, 1);
								console.log(partHolonymJsonArray);
								document.getElementById("partHolonymTags").value = JSON.stringify(partHolonymJsonArray);

							}
						});

						$('#memberMeronym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#memberMeronym').tagit("tagLabel", tag);
								memberMeronymJsonArray.push(item);
								console.log(memberMeronymJsonArray);
								document.getElementById("memberMeronymTags").value = JSON.stringify(memberMeronymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#memberMeronym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(memberMeronymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								memberMeronymJsonArray.splice(removeIndex, 1);
								console.log(memberMeronymJsonArray);
								document.getElementById("memberMeronymTags").value = JSON.stringify(memberMeronymJsonArray);

							}

						});

						$('#substanceMeronym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#substanceMeronym').tagit("tagLabel", tag);
								substanceMeronymJsonArray.push(item);
								console.log(substanceMeronymJsonArray);
								document.getElementById("substanceMeronymTags").value = JSON.stringify(substanceMeronymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#substanceMeronym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(substanceMeronymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								substanceMeronymJsonArray.splice(removeIndex, 1);
								console.log(substanceMeronymJsonArray);
								document.getElementById("substanceMeronymTags").value = JSON.stringify(substanceMeronymJsonArray);

							}

						});

						$('#partMeronym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#partMeronym').tagit("tagLabel", tag);
								partMeronymJsonArray.push(item);
								console.log(partMeronymJsonArray);
								document.getElementById("partMeronymTags").value = JSON.stringify(partMeronymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#partMeronym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(partMeronymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								partMeronymJsonArray.splice(removeIndex, 1);
								console.log(partMeronymJsonArray);
								document.getElementById("partMeronymTags").value = JSON.stringify(partMeronymJsonArray);

							}

						});

						$('#attribute').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#attribute').tagit("tagLabel", tag);
								attributeJsonArray.push(item);
								console.log(attributeJsonArray);
								document.getElementById("attributeTags").value = JSON.stringify(attributeJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#attribute').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(attributeJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								attributeJsonArray.splice(removeIndex, 1);
								console.log(attributeJsonArray);
								document.getElementById("attributeTags").value = JSON.stringify(attributeJsonArray);

							}

						});

						$('#troponym').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#troponym').tagit("tagLabel", tag);
								troponymJsonArray.push(item);
								console.log(troponymJsonArray);
								document.getElementById("troponymTags").value = JSON.stringify(troponymJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#troponym').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(troponymJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								troponymJsonArray.splice(removeIndex, 1);
								console.log(troponymJsonArray);
								document.getElementById("troponymTags").value = JSON.stringify(troponymJsonArray);

							}

						});

						$('#entailment').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#entailment').tagit("tagLabel", tag);
								entailmentJsonArray.push(item);
								console.log(entailmentJsonArray);
								document.getElementById("entailmentTags").value = JSON.stringify(entailmentJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#entailment').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(entailmentJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								entailmentJsonArray.splice(removeIndex, 1);
								console.log(entailmentJsonArray);
								document.getElementById("entailmentTags").value = JSON.stringify(entailmentJsonArray);

							}

						});

						$('#cause').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#cause').tagit("tagLabel", tag);
								causeJsonArray.push(item);
								console.log(causeJsonArray);
								document.getElementById("causeTags").value = JSON.stringify(causeJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#cause').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(causeJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								causeJsonArray.splice(removeIndex, 1);
								console.log(causeJsonArray);
								document.getElementById("causeTags").value = JSON.stringify(causeJsonArray);

							}

						});

						$('#alsosee').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#alsosee').tagit("tagLabel", tag);
								alsoseeJsonArray.push(item);
								console.log(alsoseeJsonArray);
								document.getElementById("alsoseeTags").value = JSON.stringify(alsoseeJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#alsosee').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(alsoseeJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								alsoseeJsonArray.splice(removeIndex, 1);
								console.log(alsoseeJsonArray);
								document.getElementById("alsoseeTags").value = JSON.stringify(alsoseeJsonArray);

							}

						});

						$('#similar').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#similar').tagit("tagLabel", tag);
								similarJsonArray.push(item);
								console.log(similarJsonArray);
								document.getElementById("similarTags").value = JSON.stringify(similarJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#similar').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(similarJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								similarJsonArray.splice(removeIndex, 1);
								console.log(similarJsonArray);
								document.getElementById("similarTags").value = JSON.stringify(similarJsonArray);

							}

						});

						$('#relationaladj').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#relationaladj').tagit("tagLabel", tag);
								relationaladjJsonArray.push(item);
								console.log(relationaladjJsonArray);
								document.getElementById("relationaladjTags").value = JSON.stringify(relationaladjJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#relationaladj').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(relationaladjJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								relationaladjJsonArray.splice(removeIndex, 1);
								console.log(relationaladjJsonArray);
								document.getElementById("relationaladjTags").value = JSON.stringify(relationaladjJsonArray);

							}

						});

						$('#derivedfrom').tagit({
							allowSpaces : true,
							requireAutocomplete : true,
							tagSource : function(request, response) {
								$.ajax({
									url : "RelationshipTags?action=getTags",
									dataType : "json",
									data : {
										term : request.term,
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.word + "(" + item.def + "," + item.pos + ")",
												value : item.word + "(ID:" + item.id + "," + item.pos + ")",
											};
										}));
									}

								});
							},

							onTagAdded : function(event, tag) {

								item = {};
								item["tag"] = $('#derivedfrom').tagit("tagLabel", tag);
								derivedfromJsonArray.push(item);
								console.log(derivedfromJsonArray);
								document.getElementById("derivedfromTags").value = JSON.stringify(derivedfromJsonArray);
							},

							onTagRemoved : function(event, tag) {

								var value = $('#derivedfrom').tagit("tagLabel", tag);

								console.log(value);

								var removeIndex;
								$.each(derivedfromJsonArray, function(index, result) {
									if (result["tag"] == value) {
										removeIndex = index;
									}
								});
								derivedfromJsonArray.splice(removeIndex, 1);
								console.log(derivedfromJsonArray);
								document.getElementById("derivedfromTags").value = JSON.stringify(derivedfromJsonArray);

							}

						});

					});
				</script>


				<table>
					<tbody>
						<c:if test="${(type == 'noun')  || (type == 'verb')}">
							<tr>
								<td><div class="data">Hypernym (* is a kindof...) :</div></td>
								<td><input name="tags" id="hypernym" value=""></td>
								<td><select id="hypernySuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${hypernymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
						<c:if test="${type == 'noun'}">
							<tr>
								<td><div class="data">Hyponym (... is a kind of *) :</div></td>
								<td><input name="tags" id="hyponym" value=""></td>
								<td><select id="hyponymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${hyponymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
						<c:if test="${type == 'noun'}">
							<tr>
								<td><div class="data">Holonym (Member) :</div></td>
								<td><input name="tags" id="memberHolonym" value=""></td>
								<td><select id="memberHolonymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${memberHolonymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td><div class="data">Holonym (Substance) :</div></td>
								<td><input name="tags" id="substanceHolonym" value=""></td>
								<td><select id="substanceHolonymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${substanceHolonymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td><div class="data">Holonym (Part) :</div></td>
								<td><input name="tags" id="partHolonym" value=""></td>
								<td><select id="partHolonymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${partHolonymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
						<c:if test="${type == 'noun'}">
							<tr>
								<td><div class="data">Meronym (Member) :</div></td>
								<td><input name="tags" id="memberMeronym" value=""></td>
								<td><select id="memberMeronymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${memberMeronymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td><div class="data">Meronym (Substance) :</div></td>
								<td><input name="tags" id="substanceMeronym" value=""></td>
								<td><select id="substanceMeronymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${substanceMeronymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td><div class="data">Meronym (Part) :</div></td>
								<td><input name="tags" id="partMeronym" value=""></td>
								<td><select id="partMeronymSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${partMeronymSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
						<c:if test="${(type == 'noun')  || (type == 'adj')}">
							<tr>
								<td><div class="data">Attribute :</div></td>
								<td><input name="tags" id="attribute" value=""></td>
								<td><select id="attributeSuggestions" size="1" style="width: 200px" multiple="multiple">
										<c:forEach var="suggestion" items="${attributeSuggestionList}">
											<option value=${suggestion.getOffset() }>${suggestion.getWordsAsString()}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>
						<c:if test="${type == 'verb'}">
							<tr>
								<td><div class="data">Troponym :</div></td>
								<td><input name="tags" id="troponym" value=""></td>
							</tr>
						</c:if>
						<c:if test="${type == 'verb'}">
							<tr>
								<td><div class="data">Entailment :</div></td>
								<td><input name="tags" id="entailment" value=""></td>
							</tr>
						</c:if>
						<c:if test="${type == 'verb'}">
							<tr>
								<td><div class="data">Cause :</div></td>
								<td><input name="tags" id="cause" value=""></td>
							</tr>
						</c:if>
						<c:if test="${(type == 'adj')  || (type == 'verb')}">
							<tr>
								<td><div class="data">Also See :</div></td>
								<td><input name="tags" id="alsosee" value=""></td>
							</tr>
						</c:if>
						<c:if test="${type == 'adj'}">
							<tr>
								<td><div class="data">Similar :</div></td>
								<td><input name="tags" id="similar" value=""></td>
							</tr>
						</c:if>
						<c:if test="${type == 'adj'}">
							<tr>
								<td><div class="data">Relational Adj.:</div></td>
								<td><input name="tags" id="relationaladj" value=""></td>
							</tr>
						</c:if>
						<c:if test="${type == 'adv'}">
							<tr>
								<td><div class="data">Derived from :</div></td>
								<td><input name="tags" id="derivedfrom" value=""></td>
							</tr>
						</c:if>
					</tbody>
				</table>

				<form:form method="POST" modelAttribute="tagModel" action="EditRelationship">
					<p>
						<form:input type="hidden" id="synsetId" path="synsetId" value="${synset.offset}" />
					</p>
					<p>
						<form:input type="hidden" id="type" path="synsetType" value="${type}" />
					</p>
					<p>
						<form:input type="hidden" id="hypernymTags" path="hypernymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="hyponymTags" path="hyponymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="memberHolonymTags" path="memberHolonymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="substanceHolonymTags" path="substanceHolonymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="partHolonymTags" path="partHolonymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="memberMeronymTags" path="memberMeronymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="substanceMeronymTags" path="substanceMeronymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="partMeronymTags" path="partMeronymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="attributeTags" path="attributeJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="troponymTags" path="troponymJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="entailmentTags" path="entailmentJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="causeTags" path="causeJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="alsoseeTags" path="alsoseeJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="similarTags" path="similarJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="relationaladjTags" path="relationaladjJsonString" />
					</p>
					<p>
						<form:input type="hidden" id="derivedfromTags" path="derivedfromJsonString" />
					</p>
					<p>
					<div class="button_div">
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="සුරකින්න" class="button" />
					</div>
					</p>

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>