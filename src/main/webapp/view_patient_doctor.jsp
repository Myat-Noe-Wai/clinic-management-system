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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<%@ include file="component/style.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    function confirmDelete() {
        return confirm("Are you sure you want to delete this patient?");
    }
</script>
</head>
<body>
	<%@ include file="component/doctor_navbar.jsp"%>
	<%
		if(session.getAttribute("userObj") == null){	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container-fulid p-5">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-style">
					<p class="fs-3 text-center mt-3">View Patients</p>

					<div class="row" style="margin-top: -45px;">
						<div class="col-md-6">
							<input type="hidden">
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
						if(session.getAttribute("errMsg") != null){
					%>
					<p class="text-warning"><%= session.getAttribute("errMsg") %></p>
					<%
							session.removeAttribute("errMsg");
						}
					%>
					<%
						if(session.getAttribute("sucMsg") != null){
					%>
					<p class="text-success"><%= session.getAttribute("sucMsg") %></p>
					<%
							session.removeAttribute("sucMsg");
						}
					%>
					<%
						if(session.getAttribute("searchError") != null){
					%>
					<p class="text-success"><%= session.getAttribute("searchError") %></p>
					<%
							session.removeAttribute("searchError");
						}
					%>
					<div class="card-body" style="margin-top: -23px;">
						<form action="DeletePatientDoctor" method="post">
							<table class="table table-striped table-hover" id="data-table">
								<tr class="table-primary">
									<th>Id</th>
									<th>Name</th>
									<th>Gender</th>
									<th>Age</th>
									<th>Address</th>
									<th>Phone No</th>
									<th>Action</th>
								</tr>
								<%
									PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
									List<Patient> patients = null;									
									patients = patientDao.getAllPatients();										
									
									for(Patient p : patients){
								%>
								<tr>
									<td><%=p.getId() %></td>
									<td><%=p.getName() %></td>
									<td><%=p.getGender() %></td>
									<td><%=p.getAge() %></td>
									<td><%=p.getAddress() %></td>
									<td><%=p.getPhoneNo() %></td>
									<td><a class="btn btn-secondary"
										href="patient_details_doctor.jsp?id=<%=p.getId() %>&name=<%=p.getName() %>"
										role="button"><i class="fa-regular fa-eye"></i> View</a> <a
										class="btn btn-success"
										href="edit_patient_doctor.jsp?id=<%=p.getId() %>"
										role="button"> <i class="fa-solid fa-pen-to-square"></i>
											Edit
									</a> <input type="hidden" value="<%=p.getId() %>" name="id">
										<button type="submit" class="btn btn-danger"
											onclick="return confirmDelete();">
											<i class="fa-solid fa-trash-can"></i> Delete
										</button>
								</tr>
								<% }
										
								%>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		document.getElementById("search-button").addEventListener("click", function() {
	        var input, filter, table, tr, td, i, txtValue;
	        input = document.getElementById("search-input");
	        console.log(input);
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
	</script>
</body>
</html>