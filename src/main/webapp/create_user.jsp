<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Doctor</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){
	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<div class="card card-style">
					 <form class="p-5" action="AddUser" method="post">
						 <h2 class="text-center text-primary mb-5">Create User</h2>
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
		    					<input type="text" class="form-control" name="name" required>
					      </div>
					      <div class="col mb-3">
		    					<label class="form-label">Role</label>
		    					<select required class="form-control" name="role" id="role">
									<option value="">--	Select Role	--</option>
									<option value="User">User</option>
									<option value="Doctor">Doctor</option>
								</select>
					      </div>
					    </div>
					    <div class="row">
					    	<div class="col mb-3">
		    					<label class="form-label">Email address</label>
		    					<input type="email" class="form-control" name="email" required>
					      	</div> 
					    	<div class="col mb-3">
		    					<label class="form-label">Enter Password</label>
		    					<input type="password" class="form-control" name="password" required>
					      	</div>
					    </div>
					    <div class="row">
					      <div class="col mb-3">
		    					<label class="form-label">Enter Address</label>
		    					<input type="text" class="form-control" name="address" required>
					      </div>
					      <div class="col mb-3">
		    					<label class="form-label">Enter Phone </label>
		    					<input type="text" class="form-control" name="phone" required>
					      </div>
					    </div>
					    <div class="row">
					   		<div class="col mb-3">
		    					<label class="form-label">Enter Qualification</label>
		    					<input type="text" class="form-control" name="qualification" required>
					      	</div>
					      	<div class="col mb-3">
		    					<label class="form-label">Enter Specialist</label>
		    					<input type="text" class="form-control" name="specialist" required>
					      	</div>
					    </div>
					     <button type="submit" class="btn btn-primary form-control mt-5">Save</button>
					    
					 </form>
		</div>
			</div>
		</div>
	</div>
</body>
</html>