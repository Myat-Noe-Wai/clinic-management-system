<%@page import="com.clinic.models.PatientDetails"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.models.Patient"%>
<%@page import="java.util.List"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Appointment</title>
<%@ include file="component/style.jsp" %>

</head>
<body>
	<c:out value="Hello, JSTL!" />
	<%@ include file="component/navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){
	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container-fluid p-5">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-style">
					
					<div class="row">
						<div class="col-md-4 offset-md-3">
						<p class="fs-3 text-center pt-3">View Appointments</p>
						</div>
						<div class="col-md-4 ms-auto  m-3 d-flex pt-3">
        					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="search-input">
        					<button class="btn btn-outline-success" type="submit" id="search-button">Search</button>
						</div>
					</div>
					<h4 class="text-success mb-3" id="statusMsg"></h4>
					
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
		<div class="card-body">
			<table class="table table-striped table-hover" id="data-table">
				<thead>
					<tr>
						<th>Patient Id</th>
						<th>Name</th>
						<th>Gender</th>
						<th>Age</th>
						<th>Disease</th>
						<th>Appointment Date</th>
						<th>Address</th>
						<th>Phone No</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<%
				PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
				List<PatientDetails> listPatient = patientDAO.getAllAppointment();
				for(PatientDetails p: listPatient){
				%>
					<tr>
						<td><%=p.getPatient_id()%></td>
						<td><%=p.getName() %></td>
						<td><%=p.getGender() %></td>
						<td><%=p.getAge() %></td>
						<td><%=p.getDisease() %></td>
						<td><%=p.getAppointment_date() %></td>
						<td><%=p.getAddress() %></td>
						<td><%=p.getPhoneNo() %></td>
						<td>
							<a href="update_appointment.jsp?id=<%=p.getId() %>" class="btn btn-success" role="button"><span><i class="fa-regular fa-pen-to-square"></i></span> Edit</a>
							<a class="btn btn-danger" href="#" role="button"  data-bs-toggle="modal" data-bs-target="#exampleModal" data-id="<%=p.getId() %>" onclick="showAlertAndSubmit()"><span><i class="fa-regular fa-trash-can"></i></span> Delete</a>
						</td>
					</tr>
				<% } %>
					
				</tbody>
			</table>
			</div>
			</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Are you sure want to delete?</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        
	      </div>
	      <div class="modal-footer">
	      	<form action="DeleteAppointment" method="post">
	      		<input type="hidden" name="patientDetailId" id="patientDetailId">
	      		<a href="#" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</a>
	        	<button type="submit" class="btn btn-primary" >OK</button>
	      	</form>
	       
	      </div>
	    </div>
	  </div>
	</div>
	<script>
    document.getElementById("search-button").addEventListener("click", function() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("search-input");
        filter = input.value.toUpperCase();
        table = document.getElementById("data-table");
        tr = table.getElementsByTagName("tr");

        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1]; // Change index to target the desired column (e.g., [0] for the first column)
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    });
    
    function showAlertAndSubmit(){
    	 var deleteButton = event.currentTarget; // Get the clicked button
    	 var itemId = deleteButton.getAttribute("data-id");
    	 document.getElementById("patientDetailId").value = itemId;
    }
   
</script>
</body>
</html>