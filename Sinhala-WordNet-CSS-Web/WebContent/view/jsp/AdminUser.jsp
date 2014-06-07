<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin User Settings</title>

<link rel="stylesheet" type="text/css" href="theme/css/wordnetstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
<script type="text/javascript" src="theme/scripts/autoBredcrums.js"></script>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">

<!-- jQuery -->
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>

<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	var oTable;
	var giRedraw = false;
	$(document).ready(function() {
		$(".clickableRow").click(function() {
			window.document.location = $(this).attr("href");
		});
		$("#example tbody").click(function(event) {
			$(oTable.fnSettings().aoData).each(function() {
				$(this.nTr).removeClass('row_selected');
			});
			$(event.target.parentNode).addClass('row_selected');
		});
		oTable = $('#example').dataTable();

	});
	function fnGetSelected(oTableLocal) {
		var aReturn = new Array();
		var aTrs = oTableLocal.fnGetNodes();

		for ( var i = 0; i < aTrs.length; i++) {
			if ($(aTrs[i]).hasClass('row_selected')) {
				aReturn.push(aTrs[i]);
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
		<div id="summary">
			<div class="whitebox">
				<h3>Click a row to edit user</h3>
			</div>

		</div>
		<div id="menu">
			<table cellpadding="0" cellspacing="0" border="1" class="display"
				id="example">
				<thead>
					<tr>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Role</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="user" items="${userList}" varStatus="loop">
						<tr class='clickableRow' href='AdminOptions?action=ShowEditUsers&username=<c:out value="${user.username}"/>'>
							<td>${user.getUsername()}</td>
							<td>${user.getFirstName()}</td>
							<td>${user.getLastName()}</td>
							<td>${user.getEmail()}</td>
							<td>${user.getRole()}</td>
						</tr>
					</c:forEach>



				</tbody>
				<tfoot>
					<tr>
						<tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
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