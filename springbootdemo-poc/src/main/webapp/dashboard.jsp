<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head>
<title>User Details</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<script>
	function ConfirmDelete() {
		var x = confirm("Are you sure you want to delete?");
		return x ? true : false;
	}
</script>

</head>
<body>
	<div>
		<div class="container" style="margin: 40px">
			<div>
				<form action="/logout" method="post">
					<button type="submit" class="btn btn-danger">Log Out</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
			<div class="row text-center">
				<strong> User Details</strong>
			</div>
			<div class="row" style="border: 1px solid green; padding: 10px">
				<div class="col-md-2 text-center">
					<strong>User Name</strong>
				</div>
				<div class="col-md-2 text-center">
					<strong>Mobile</strong>
				</div>
				<div class="col-md-2 text-center">
					<strong>Email</strong>
				</div>
				<div class="col-md-2 text-center">
					<strong>Address</strong>
				</div>
				<div class="col-md-2 text-center">
					<strong>Actions</strong>
				</div>
			</div>
			<c:forEach var="user" items="${users}">
				<div class="row" style="border: 1px solid green; padding: 5px">
					<div class="col-md-2 text-center">${user.username}</div>
					<div class="col-md-2 text-center">${user.mobile}</div>
					<div class="col-md-2 text-center">${user.email}</div>
					<div class="col-md-2 text-center">${user.address}</div>
					<div style="width: 915px;">
						<div style="float: left; width: 70px">
							<form:form method="DELETE" action="/delete/${user.id}"
								modelAttribute="user">
								<input type="submit" class="btn btn-danger btn-xs"
									value="DELETE" Onclick="return ConfirmDelete();" />
							</form:form>
						</div>
						<div style="float: right; width: 70px">
							<form:form method="POST" action="/edit/${user.id}"
								modelAttribute="userForm">
								<input type="submit" class="btn btn-info btn-xs" value="EDIT" />
							</form:form>
						</div>
					</div>
				</div>
			</c:forEach>


		</div>
	</div>


</body>
</html>