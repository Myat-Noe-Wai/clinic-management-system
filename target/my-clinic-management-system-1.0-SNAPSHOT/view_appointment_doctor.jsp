<%@page import="com.clinic.models.PatientDetails"%>
<%@page import="com.clinic.models.Patient"%>
<%@page import="java.util.List"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Patients</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<%@ include file="component/style.jsp" %>
</head>
<body>
	<%@ include file="component/doctor_navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container-fulid p-5">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-style">
					<p class="fs-3 text-center" style="margin-top: 12px;">View Appointments</p>
						<%
							if(session.getAttribute("errMsg")!=null){
						%>
						<p class="text-warning"><%= session.getAttribute("errMsg") %></p>
						<%
								session.removeAttribute("errMsg");
							}
						%>
						<%
							if(session.getAttribute("sucMsg")!=null){
						%>
							<p class="text-success"><%= session.getAttribute("sucMsg") %></p>
						<%
								session.removeAttribute("sucMsg");
							}
						%>
					<div class="card-body" style="margin-top: -15px;">
						<table class="table table-striped table-hover">
							<tr class="table-primary">
								<th>Id</th>
								<th>Name</th>
								<th>Gender</th>
								<th>Age</th>
								<th>Address</th>
								<th>Phone No</th>
								<th>Disease</th>
								<th>Appointment Date</th>
								<th>Status</th>
								<th>Action</th>
							</tr>
							<%
								PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
								List<Patient> patients = patientDao.getAllPendingPatients();
								List<PatientDetails> pdList = patientDao.getAllPendingPatientDetails();
								for(int i=0; i<patients.size(); i++){
							%>
							<tr>
								<td><%=patients.get(i).getId() %></td>
								<td><%=patients.get(i).getName() %></td>
								<td><%=patients.get(i).getGender() %></td>
								<td><%=patients.get(i).getAge() %></td>
								<td><%=patients.get(i).getAddress() %></td>
								<td><%=patients.get(i).getPhoneNo() %></td>
								<td><%=pdList.get(i).getDisease() %></td>
								<td><%=pdList.get(i).getAppointment_date() %></td>
								<td><%=pdList.get(i).getStatus() %></td>
								<td><a class="btn btn-success" href="add_prescription.jsp?patientId=<%=patients.get(i).getId() %>&pDetailId=<%=pdList.get(i).getId() %>&pname=<%=patients.get(i).getName() %>&disease=<%=pdList.get(i).getDisease() %>" role="button">Add Prescription to Patient</a></td>
							<%} %>																								
							</tr>														
						</table>
					</div>
				</div>
			</div>								
		</div>
	</div>
</body>
</html>