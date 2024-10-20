<%@page import="com.clinic.models.Patient"%>
<%@page import="java.util.List"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PharmaceuticalDAO"%>
<%@page import="com.clinic.models.Pharmaceutical"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Pharmaceutical</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<%@ include file="component/style.jsp" %>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	<div class="container">
		<div class="row m-4">
			<div class="col-md-8 ms-5">
				<div class="card card-style">
					<div class="card-body">
						<p class="fs-3 text-center">Pharmaceutical Information</p>
						<%
							int pharId = Integer.parseInt(request.getParameter("id"));
							PharmaceuticalDAO pharDao = new PharmaceuticalDAO(DBConnect.getConnection());
							Pharmaceutical phar = pharDao.getPharmaceutical(pharId);
						%>
						<form class="row g-3 p-4" method="post" action="UpdatePharmaceutical">
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Name : </label>
								</div>
								<div class="col-md-5">									
									<input type="text" class="form-control" value="<%=phar.getName() %>" name="name">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Vendor : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=phar.getVendor() %>" name="vendor">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Category : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=phar.getCategory() %>" name="category">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Quantity : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=phar.getQty() %>" name="qty">
									<input type="hidden" value="<%=phar.getId() %>" name="id">
								</div>
							</div>													
							<div class="row mb-3">
								<div class="col-md-5 text-center">									
									<a href="pharmaceuticals.jsp" class="btn btn-danger">Cancel</a>
								</div>
								<div class="col-md-5 text-center">																											
									<button type="submit" class="btn btn-success ms-5">Update</button>
								</div>
							</div>							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>