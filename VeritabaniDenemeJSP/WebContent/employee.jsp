<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee</title>

<script src="resources/js/bootstrap.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
			</div>

				<div class="row">
					<div class="col-md-6">
					<legend>Employee List</legend>
						<div class="table-responsive">
							<table id="example" class="table table-striped table-bordered">							
							<thead>
								<tr>
									<th>Name</th>
									<th>Surname</th>
									<th>Salary</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${allEmployees}" var="employee">
									<tr>
										<td><c:out value="${employee.name}" /></td>
										<td><c:out value="${employee.surname}" /></td>
										<td><c:out value="${employee.salary}" /></td>
										<td>
										  
									        <a href="employeeController?action=delete&employeeId=${employee.id}"
										     class="btn btn-danger" type="button">Sil</a>
											<a href="employeeController?action=update&employeeId=${employee.id}"
										     class="btn btn-primary" type="button">Guncelle</a>
										</td>
											
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
					</div>
					<div class="col-md-6"></div>
				</div>
				<a href="employeeController?action=insert"
										     class="btn btn-primary" type="button">Ekle</a>
										     <a href="employeeController?action=listEmployee"
										     class="btn btn-secondary" type="button">Ana Sayfaya Don</a>
										     <a href="admin/baskaController"
										     class="btn btn-secondary" type="button">Diğer controllera git</a>
			</div>
		</div>
	</div>

</body>
</html>