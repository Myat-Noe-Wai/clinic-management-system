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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    function confirmUpdate() {
        return confirm("Are you sure you want to update this patient?");
    }
</script>
</head>
<body>
	<%@ include file="component/doctor_navbar.jsp" %>
	<div class="container">
		<div class="row m-4">
			<div class="col-md-8 ms-5">
				<div class="card card-style">
					<div class="card-body">
						<p class="fs-3 text-center">Patient Details Information</p>
						<%
							int patient_id = Integer.parseInt(request.getParameter("id"));
							PatientDAO dao = new PatientDAO(DBConnect.getConnection());
							Patient p = dao.getPatientById(patient_id);											
						%>
						<form class="row g-3 p-4" method="post" action="UpdatePatientDoctor">
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Patient ID : </label>
								</div>
								<div class="col-md-5">									
									<input type="text" class="form-control" value="<%=p.getId() %>" name="patientId">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Patient Name : </label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=p.getName() %>" name="name">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Gender</label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=p.getGender() %>" name="gender">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Age</label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=p.getAge() %>" name="age">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Address</label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=p.getAddress() %>" name="address">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-5">
									<label class="form-label">Phone No</label>
								</div>
								<div class="col-md-5">
									<input type="text" class="form-control" value="<%=p.getPhoneNo() %>" name="phoneNo">
								</div>
							</div>							
							<div class="row mb-3">
								<div class="col-md-5 text-center">									
									<a href=view_patient_doctor.jsp class="btn btn-danger">Cancel</a>
								</div>
								<div class="col-md-5 text-center">																											
									<button type="submit" class="btn btn-success ms-5" onclick="return confirmUpdate();">Update</button>
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