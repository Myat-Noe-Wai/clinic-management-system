<%@page import="com.clinic.dao.UserDAO"%>
<%@page import="com.clinic.dao.DoctorDAO"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	<%
	PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
	Integer patientCount = patientDAO.getPatientCount();
	
	Integer appointmentCount = patientDAO.getAppointmentCount();
	
	UserDAO userDAO = new UserDAO(DBConnect.getConnection());
	Integer doctorCount = userDAO.getDoctorCount();
	
	if(session.getAttribute("userObj") == null){
	
		response.sendRedirect("user_login.jsp");
	}
	%>
	
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-4">
				<div class="card card-style">
					<div class="card-body">
					 	<!-- <h5 class="card-title text-center">Patients</h5> -->
					 	<div class="row">
					 		<div class="col-md-6 p-5"><h2>   <%=patientCount %></h2>
					 		Total Patients
					 		</div>
					 		<div class="col-md-6 p-5"><i class="fa-solid fa-hospital-user fs-1 text-primary"></i></div>
					 	</div> 		 	
					</div>
				</div>
			</div>
				<div class="col-md-4">
				<div class="card card-style">
					<div class="card-body">
					 	<!-- <h5 class="card-title text-center">Patients</h5> -->
					 	<div class="row">
					 		<div class="col-md-6 p-5"><h2>   <%=appointmentCount %></h2>
					 		 Appointments
					 		</div>
					 		<div class="col-md-6 p-5"><i class="fa-solid fa-calendar-days fs-1 text-primary"></i></div>
					 	</div> 		 	
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card card-style">
					<div class="card-body">
					 	<!-- <h5 class="card-title text-center">Patients</h5> -->
					 	<div class="row">
					 		<div class="col-md-6 p-5"><h2>   <%=doctorCount %></h2>
					 		Total Doctor
					 		</div>
					 		<div class="col-md-6 p-5"><i class="fa-solid fa-user-doctor fs-1 text-primary"></i></div>
					 	</div> 		 	
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>