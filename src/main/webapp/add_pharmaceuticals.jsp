<%@page import="com.clinic.models.Patient"%>
<%@page import="java.util.List"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@page import="com.clinic.models.PatientDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Patients</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<%@ include file="component/style.jsp" %>
<script type="text/javascript">
    function confirmUpdate() {
        return confirm("Are you sure you want to update this patient?");
    }
</script>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	<div class="container">
		<div class="row m-4">
			<div class="col-md-8 ms-5">
				<div class="card card-style">
					<div class="card-body">										
						<form class="row g-3 p-4" method="post" action="AddPharmaceutical">
							<h2 class="text-center text-primary mb-5">Add Pharmaceuticals</h2>	
							<%
							if(session.getAttribute("sucMsg") != null){
							%>
							<h4 class="text-success mb-3"><%=session.getAttribute("sucMsg") %></h4>
							
							<%
							session.removeAttribute("sucMsg");
							}
							%>
							<%
							if(session.getAttribute("errMsg") != null){
							%>
							<h4 class="text-warning mb-3"><%=session.getAttribute("errMsg") %></h4>
							<%
							session.removeAttribute("errMsg");
							}
							%>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Name : </label>
								</div>
								<div class="col-md-5">									
									<input type="text" class="form-control" name="name">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Vendor : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" name="vendor">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Category : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" name="category">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Pharceutical Quantity : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" name="qty">
								</div>
							</div>												
							<div class="row mb-3">
								<div class="col-md-5 text-center">									
									<a href="pharmaceuticals.jsp" class="btn btn-danger">Cancel</a>
								</div>
								<div class="col-md-5 text-center">																											
									<button type="submit" class="btn btn-success ms-5">Add</button>
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