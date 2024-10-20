<%@page import="com.clinic.models.PatientDetails"%>
<%@page import="com.clinic.models.Patient"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Appointment</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
<%@ include file="component/navbar.jsp" %>
<div class="container">
		<div class="row m-5">
			<div class="col-md-6 offset-md-4">
				<div class="card card-style">
					<div class="card-body">
						<form class="row g-3 " action="UpdateAppointment" method="post">
							 <h2 class="text-center text-primary">Update Appointment</h2>
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
							<%
							PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
							Integer id = Integer.parseInt(request.getParameter("id"));
							PatientDetails p = patientDAO.getAppointmentByPatientId(id);
							%>
						    <div class="col-md-6 mb-3">
						    	<label for="name" class="form-label">Name</label> 
						     	<input type="text" class="form-control" placeholder="Enter Name" name="name" value="<%=p.getName()%>" readonly>
						    </div>
						    <div class="col-md-6 mb-3">
						      <label class="form-label">Gender</label> <select class="form-control" name="gender" readonly>
									<%
									if(p.getGender().equals("male")){
									%>
									<option value="male">Male</option>
									<!-- <option value="female">Female</option> -->
									<% }else{ %>
									<option value="female">Female</option>
									<!-- <option value="male">Male</option> -->
									<% } %>
								</select>
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label  class="form-label">Age</label> 
						      	<input type="text" class="form-control" placeholder="Enter age" name="age" value="<%=p.getAge()%>" readonly>
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label class="form-label">Address</label> 
						      	<input type=address class="form-control" placeholder="Enter address" name="address" value="<%=p.getAddress()%>" readonly>
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label for="phoneNo" class="form-label">Phone No</label> 
						      	<input type="text" class="form-control" placeholder="Enter phone" name="phoneNo" value="<%=p.getPhoneNo()%>" readonly>
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label for="patientId" class="form-label">Patient Id</label> 
						      	<input type="text" class="form-control" name="patientid" value="<%=p.getPatient_id()%>" readonly>
						    </div>
						  	<div class="col-md-6 mb-3">
						    	<label for="appointmentDate" class="form-label">Appointment Date</label> 
						      	<input type="date" class="form-control" placeholder="Enter appointment date" name="appointmentDate" value="<%=p.getAppointment_date()%>">
						    </div>
						     <div class="col-md-6 mb-3">
						    	<label for="disease" class="form-label">Disease</label> 
						      	<input type="text" class="form-control" name="disease" value="<%=p.getDisease()%>">
						    </div>

						    <input type="hidden" name="patientDetailId" value="<%=p.getId()%>">
						    <button type="submit" class="btn btn-primary col-md-12  mt-5">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>