<%@page import="com.clinic.dao.UserDAO"%>
<%@page import="com.clinic.models.Doctor"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.DoctorDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Doctor</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
<%@ include file="component/navbar.jsp" %>
<%
	System.out.println(request.getParameter("id")+"doctor id");
	int userId = Integer.parseInt(request.getParameter("id"));
	UserDAO userDAO = new UserDAO(DBConnect.getConnection());
	User u = userDAO.getUserById(userId);
%>
<div class="container p-5">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<div class="card card-style">
					 <form class="p-5" action="UpdateUser" method="post">
						 <h2 class="text-center text-primary mb-5">Update User</h2>
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
					    <div class="row">
					      <div class="col mb-3">
		    					<label class="form-label">Enter Name</label>
		    					<input type="text" class="form-control" name="name" value="<%=u.getName() %>" required>
					      </div>
					      <div class="col mb-3">
					      	<label class="form-label">Role</label> 
					      	<select class="form-control" name="role"
									required>
									<%
									if(u.getRole().equals("admin")){
									%>
									<option value="admin">Admin</option>
									<option value="doctor">Doctor</option>
									<% }else{ %>
									<option value="doctor">Doctor</option>
									<option value="admin">Admin</option>
									<% } %>
								</select>
					      </div>
					    </div>
					    <div class="row">
					    	 <div class="col mb-3">
		    					<label class="form-label">Email address</label>
		    					<input type="email" class="form-control" name="email" value="<%=u.getEmail() %>" required>
					      	</div>
					    	<div class="col mb-3">
		    					<label class="form-label">Enter Password</label>
		    					<input type="password" class="form-control" name="password" value="<%=u.getPassword() %>" required>
					      	</div>
					    </div>
					    <div class="row">
					        <div class="col mb-3">
		    					<label class="form-label">Enter Address</label>
		    					<input type="text" class="form-control" name="address" value="<%=u.getAddress() %>" required>
					      </div>
					      <div class="col mb-3">
		    					<label class="form-label">Enter Phone </label>
		    					<input type="text" class="form-control" name="phone" value="<%=u.getPhone_no() %>" required>
					      </div>
					    </div>
					    <div class="row">
					    	<div class="col mb-3">
		    					<label class="form-label">Enter Qualification</label>
		    					<input type="text" class="form-control" name="qualification" value="<%=u.getQualification() %>" required>
					      	</div>
					      	<div class="col mb-3">
		    					<label class="form-label">Enter Specialist</label>
		    					<input type="text" class="form-control" name="specialist" value="<%=u.getSpecialist() %>" required>
					      	</div>
					    </div>
					    <input type="hidden" name="userId" value="<%=u.getId()%>">
					     <button type="submit" class="btn btn-primary form-control mt-5">Update</button>
					 </form>
		</div>
			</div>
		</div>
	</div>
</body>
</html>