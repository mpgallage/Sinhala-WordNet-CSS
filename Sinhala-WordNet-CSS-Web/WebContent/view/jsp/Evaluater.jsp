<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Evaluater Page</title>

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
					

<table cellpadding="0" cellspacing="0" border="1" class="display" id="example">
	<thead>
		<tr>
			<th>English WN ID</th>
			<th>Words</th>
			<th>gloss</th>
			<th>Edited By</th>
			
		</tr>
	</thead>
	<tbody>

					<c:forEach var="synset" items="${synsetList}" varStatus="loop">
					<tr>
					<td><a href='EditSynsets${type}?action=ShowEditSynset&type=<c:out value="${type}"/>&id=<c:out value="${synset.getEWNId()}"/>'>${synset.getEWNId()}</a></td>
					<td>${synset.getWordsAsString()}</td>
					<td>${synset.getGloss()}</td>
					<td>${synset.getUserName()}</td>
					</tr>
					</c:forEach>
					
					
					
	</tbody>
	<tfoot>
		<tr>
			<th>English WN ID</th>
			<th>Words</th>
			<th>gloss</th>
			<th>Edited By</th>
			
		</tr>
	</tfoot>
</table>
			


</body>
</html>