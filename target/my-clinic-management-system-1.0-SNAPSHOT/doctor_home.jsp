<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@page import="com.clinic.db.DBConnect"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
	<%@ include file="component/doctor_navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<%
		/* User user = (User) session.getAttribute("userObj"); */
		PatientDAO dao = new PatientDAO(DBConnect.getConnection());
	%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-2">
				<div class="card paint-card card-style">
					<div class="card-body text-center text-success">
						</i><i class="fa-solid fa-user fa-2xl pt-4"></i><br>
						<p class="pt-3 fs-4 text-center">
							Total Patient <br> <%=dao.getPatientCount() %> 
						</p>
					</div>
				</div>
			</div>


			<div class="col-md-4">
				<div class="card paint-card card-style">
					<div class="card-body text-center text-success">
						<i class="far fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Total Appointment <br>
							<%=dao.getAppointmentCount() %>
						</p>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>