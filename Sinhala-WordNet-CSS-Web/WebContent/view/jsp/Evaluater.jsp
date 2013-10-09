<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evaluater Page</title>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
 
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
 
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $('#example').dataTable();
});
</script>
</head>
<body>


<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th>Synset</th>
			<th>Browser</th>
			
		</tr>
	</thead>
	<tbody>
	<tr>
					<td>"${synsetList[0].toString()}"</td>
					<td>"${synsetList.size()}"</td>
					</tr>
		<c:choose>
					<c:when test="${synsetList.size()>0}">
					<c:forEach items="${synsetList}" varStatus="loop">
					<tr>
					<td>"${synsetList[1].toString()}"</td>
					<td>"${loop.index}"</td>
					</tr>
					</c:forEach>
					</c:when>
					</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<th>Synset</th>
			<th>Browser</th>
			
		</tr>
	</tfoot>
</table>
			


</body>
</html>