<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Evaluater Page</title>

<link rel="stylesheet" type="text/css" href="theme/css/wordnetstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
<script type="text/javascript" src="theme/scripts/autoBredcrums.js"></script>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
 
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript">
var oTable;
var giRedraw = false;
$(document).ready(function(){
	$("#example tbody").click(function(event) {
		$(oTable.fnSettings().aoData).each(function (){
			$(this.nTr).removeClass('row_selected');
		});
		$(event.target.parentNode).addClass('row_selected');
	});
	oTable = $('#example').dataTable( );
});
function fnGetSelected( oTableLocal )
{
	var aReturn = new Array();
	var aTrs = oTableLocal.fnGetNodes();
	
	for ( var i=0 ; i<aTrs.length ; i++ )
	{
		if ( $(aTrs[i]).hasClass('row_selected') )
		{
			aReturn.push( aTrs[i] );
		}
	}
	return aReturn;
}
</script>
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
							property="principal.username" /></b></td>
				</sec:authorize>
				<td><a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
				</td>
			</tr>
		</table>
	</div>
	<div id="warp">	
	<div id="modes">
	<table>
	<tr>
	<td><input type="button" class="button" value="All"
									onclick="window.location.href='EvaluaterMode?action=ShowEvaluater&type=<c:out value="${type}"/>&mode=all'" />
								</td>
	<td><input type="button" class="button" value="Not Evaluated"
									onclick="window.location.href='EvaluaterMode?action=ShowEvaluater&type=<c:out value="${type}"/>&mode=notevaluated'" />
								</td>
	<td><input type="button" class="button" value="Evaluated"
									onclick="window.location.href='EvaluaterMode?action=ShowEvaluater&type=<c:out value="${type}"/>&mode=evaluated'" />
								</td>
	<td align="left"><input type="button" class="button" value="Home"
									onclick="window.location.href='ShowSynsets?action=ShowRoot'" align="left" />
								</td>
	</tr>
	</table>
	</div>			
	<div id="menu">
<table cellpadding="0" cellspacing="0" border="1" class="display" id="example">
	<thead>
		<tr>
			<th>English WN ID</th>
			<th>Words</th>
			<th>gloss</th>
			<th>Edited By</th>
			<th>Edited Date</th>
			<th>Evaluated</th>
			<th>EvaluatedBy</th>
			<th>Comment</th>
			<th>Raiting</th>
			
		</tr>
	</thead>
	<tbody>

					<c:forEach var="synset" items="${synsetList}" varStatus="loop">
					<tr>
					<td><a href='EditSynsets${type}?action=ShowEditSynset&type=<c:out value="${type}"/>&id=<c:out value="${synset.getEWNId()}"/>'>${synset.getEWNId()}</a></td>
					<td>${synset.getWordsAsString()}</td>
					<td>${synset.getGloss()}</td>
					<td>${synset.getUserName()}</td>
					<td>${synset.getDate()}</td>
					<td>${synset.getEvaluated()}</td>
					<td>${synset.getEvaluatedBY()}</td>
					<td>${synset.getComment()}</td>
					<td>${synset.getRating()}</td>
					</tr>
					</c:forEach>
					
					
					
	</tbody>
	<tfoot>
		<tr>
			<th>English WN ID</th>
			<th>Words</th>
			<th>gloss</th>
			<th>Edited By</th>
			<th>Edited Date</th>
			<th>Evaluated</th>
			<th>EvaluatedBy</th>
			<th>Comment</th>
			<th>Raiting</th>
			
		</tr>
	</tfoot>
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