<%@page import="com.clinic.models.Patient"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Patient</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
<%@ include file="component/navbar.jsp" %>
<div class="container">
		<div class="row m-5">
			<div class="col-md-6 offset-md-4">
				<div class="card card-style">
					<div class="card-body">
						<form class="row g-3 " action="UpdatePatient" method="post">
							 <h2 class="text-center text-primary">Update Patient</h2>
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
							Patient p = patientDAO.getPatientById(id);
							%>
						    <div class="col-md-6 mb-3">
						    	<label for="name" class="form-label">Name</label> 
						     	<input type="text" class="form-control" placeholder="Enter Name" name="name" value="<%=p.getName()%>">
						    </div>
						    <div class="col-md-6 mb-3">
						      <label class="form-label">Gender</label> <select class="form-control" name="gender"
									required>
									<%
									if(p.getGender().equals("male")){
									%>
									<option value="male">Male</option>
									<option value="female">Female</option>
									<% }else{ %>
									<option value="female">Female</option>
									<option value="male">Male</option>
									<% } %>
								</select>
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label  class="form-label">Age</label> 
						      	<input type="text" class="form-control" placeholder="Enter age" name="age" value="<%=p.getAge()%>">
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label class="form-label">Address</label> 
						      	<input type=address class="form-control" placeholder="Enter address" name="address" value="<%=p.getAddress()%>">
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label for="phoneNo" class="form-label">Phone No</label> 
						      	<input type="text" class="form-control" placeholder="Enter phone" name="phoneNo" value="<%=p.getPhoneNo()%>">
						    </div>
						  <!--   <div class="col-md-6 mb-3">
						    	<label for="appointmentDate" class="form-label">Appointment Date</label> 
						      	<input type="date" class="form-control" placeholder="Enter appointment date" name="appointmentDate">
						    </div> -->
						    <input type="hidden" name="patientId" value="<%=p.getId()%>">
						    <button type="submit" class="btn btn-primary col-md-12  mt-5">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>