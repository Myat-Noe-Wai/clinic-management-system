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
</head>
<body>
	<%@ include file="component/doctor_navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){	
			response.sendRedirect("user_login.jsp");
		}
	%>	
	<div class="container-fluid">
		<div class="row m-4">
			<div class="col-md-12">
				<div class="card card-style">
					<div class="card-body">
						<p class="fs-3 text-center">Patient Details Information</p>
						<div class="row" style="margin-top: -45px;">
							<div class="col-md-6">
								<!-- <input type="hidden"> -->
								<a href="view_patient_doctor.jsp" class="btn btn-primary" style="margin-top: 33px;"><i class="fa-solid fa-arrow-left"></i> Back</a>
							</div>
							<div class="col-md-6">
								<div class="col-md-4 ms-auto  m-3 d-flex pt-3">
									<input class="form-control me-2" type="search"
										placeholder="Search" aria-label="Search" id="search-input">
									<button class="btn btn-outline-success" type="submit"
										id="search-button">Search</button>
								</div>
							</div>
						</div>
						<%
							int patient_id = Integer.parseInt(request.getParameter("id"));
							String name = request.getParameter("name");
							PatientDAO dao = new PatientDAO(DBConnect.getConnection());
							List<PatientDetails> pdList = dao.getPatientDetailsById(patient_id);							
						%>
						<table class="table table-striped table-hover" id="data-table">
							<tr class="table-primary">
								<th>Patient Details Id</th>
								<th>Patient Name</th>
								<th>Disease</th>
								<th>Appointment Date</th>
								<th>Status</th>
								<th>View Medicines</th>
							</tr>
							<% for (PatientDetails pd: pdList){ %>
							<tr>
								<td><%=pd.getId() %></td>
								<td><%=name %></td>
								<td><%=pd.getDisease() %></td>
								<td><%=pd.getAppointment_date() %></td>
								<td><%=pd.getStatus() %></td>
								<td><a class="btn btn-primary" onclick="getPrescription(<%=pd.getId() %>)">View</a></td>
							</tr>
							<%} %>
						</table>
					</div>
				</div>
			</div>			
		</div>
	</div>
	<div class="modal" id="dataModal" tabindex="-1" aria-labelledby="dataModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="dataModalLabel">Prescriptions</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                
	                <table id="tableId" class="table">
	                	<thead>
	                		<tr>
	                			<th>Id</th>
	                			<th>Medicine</th>
	                			<th>Quantity</th>
	                			<th>Instruction</th>
	                		</tr>
	                	</thead>
	                	<tbody id="tableBody">
	                	</tbody>
	                	
	                </table>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	            </div>
	        </div>
	    </div>
	</div>
	<script>
		function getPrescription(patientDetailId){
		  $.ajax({
	            type: 'POST',
	            url: 'GetPrescription',
	            data: { patientDetailId: patientDetailId},
	            success: function(response) {
	              console.log(response.length); 
	              $('#tableBody').empty();
	              for(var i = 0; i < response.length; i++){
	                  $('#tableBody').append('<tr>' +
	                      '<td>' + response[i]["id"] + '</td>' +
	                      '<td>' + response[i]["medicine"] + '</td>' +
	                      '<td>' + response[i]["quantity"] + '</td>' +
	                      '<td>' + response[i]["instruction"] + '</td>' +
	                      '</tr>');
	              }
	              $('#dataModal').modal('show');
	            	 
	            },
	            error: function() {
	              alert('Request failed'); }
	          });  
		}
		
		document.getElementById("search-button").addEventListener("click", function() {
	        var input, filter, table, tr, td, i, txtValue;
	        input = document.getElementById("search-input");
	        console.log(input);
	        filter = input.value.toUpperCase();
	        table = document.getElementById("data-table");
	        tr = table.getElementsByTagName("tr");
	        for (i = 0; i < tr.length; i++) {
	            td = tr[i].getElementsByTagName("td")[2];
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
	</script>
</body>
</html>