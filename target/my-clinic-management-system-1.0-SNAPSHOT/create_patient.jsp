<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Patient</title>
<%@include file="component/style.jsp" %>
</head>
<body>
	<%@include file="component/navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){
	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container">
		<div class="row m-5">
			<div class="col-md-6 offset-md-4">
				<div class="card card-style">
					<div class="card-body">
						<form class="row g-3 " action="CreatePatient" method="post">
							 <h2 class="text-center text-primary">Create Patient</h2>
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
						    <div class="col-md-6 mb-3">
						    	<label for="name" class="form-label">Name</label> 
						     	<input type="text" class="form-control" placeholder="Enter Name" name="name">
						    </div>
						    <div class="col-md-6 mb-3">
						      <label class="form-label">Gender</label> <select class="form-control" name="gender"
									required>
									<option value="male">Male</option>
									<option value="female">Female</option>
								</select>
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label  class="form-label">Age</label> 
						      	<input type="text" class="form-control" placeholder="Enter age" name="age">
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label class="form-label">Address</label> 
						      	<input type=address class="form-control" placeholder="Enter address" name="address">
						    </div>
						    <div class="col-md-6 mb-3">
						    	<label for="phoneNo" class="form-label">Phone No</label> 
						      	<input type="text" class="form-control" placeholder="Enter phone" name="phoneNo">
						    </div>
						  <!--   <div class="col-md-6 mb-3">
						    	<label for="appointmentDate" class="form-label">Appointment Date</label> 
						      	<input type="date" class="form-control" placeholder="Enter appointment date" name="appointmentDate">
						    </div> -->
						    <button type="submit" class="btn btn-primary col-md-12  mt-5">Create</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>