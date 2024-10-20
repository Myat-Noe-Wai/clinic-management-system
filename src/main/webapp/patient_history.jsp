<%@page import="com.clinic.models.Prescription"%>
<%@page import="com.clinic.models.PatientHistory"%>
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
<title>Patient History</title>
<%@ include file="component/style.jsp" %>
<style>
.gradient-custom {
/* fallback for old browsers */
background: #f6d365;

/* Chrome 10-25, Safari 5.1-6 */
background: -webkit-linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));

/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
background: linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1))
}
</style>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	<%
	int patient_id = Integer.parseInt(request.getParameter("patient_id"));
	PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
	Patient p = patientDao.getPatientById(patient_id);
	%>
	
			<section class="vh-10012 ">
				  <div class=" py-5 h-100 p-5">
				    <div class="row d-flex justify-content-start  h-100">
				      <div class="col col-lg-4 mb-4 mb-lg-0">
				        <div class="card card-style mb-3" style="border-radius: .5rem;">
				          <div class="row g-0">
				            <%-- <div class="col-md-4 gradient-custom text-center text-white"
				              style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
				              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
				                alt="Avatar" class="img-fluid my-5" style="width: 80px;" />
				              <h5><%=p.getName() %></h5>
				              <p><%=p.getPhoneNo() %></p>
				              <i class="far fa-edit mb-5"></i>
				            </div> --%>
				            <div class="col-md-12">
				            <div class="card-header p-4" style="background:#0d6efd!important;">
				            <h6 class="text-white text-center">Patient Information</h6>
				            </div>
				             <div class="card-body p-4">
				                <div class="row pt-1">
				                  <div class="col-4 mb-3">
				                    <h6>Name</h6>
				                    <p class="text-muted"><%=p.getName() %></p>
				                  </div>
				                  <div class="col-4 mb-3">
				                    <h6>Gender</h6>
				                    <p class="text-muted"><%=p.getGender() %></p>
				                  </div>
				                  <div class="col-4 mb-3">
				                    <h6>Age</h6>
				                    <p class="text-muted"><%=p.getAge() %></p>
				                  </div>
				                </div>
				                <div class="row pt-1">
				                  <div class="col-4 mb-3">
				                    <h6>Address</h6>
				                    <p class="text-muted"><%=p.getAddress() %></p>
				                  </div>
				                  <div class="col-4 mb-3">
				                    <h6>Phone Number</h6>
				                    <p class="text-muted"><%=p.getPhoneNo() %></p>
				                  </div>
				                  
				                </div>
				              <!--   <div class="d-flex justify-content-start">
				                  <a href="#!"><i class="fab fa-facebook-f fa-lg me-3"></i></a>
				                  <a href="#!"><i class="fab fa-twitter fa-lg me-3"></i></a>
				                  <a href="#!"><i class="fab fa-instagram fa-lg"></i></a>
				                </div> -->
				              </div>
				            </div>
				          </div>
				        </div>
				      </div>
				      <div class="col col-lg-8 mb-4 mb-lg-0">
				      	<div class="row">
							<div class="col-md-12">
								<div class="card card-style" style="border: 1px solid rgba(0,0,0,.125);">
									
									<div class="row">
										<div class="col-md-4 offset-md-3">
										<p class="fs-3 text-center pt-3">Patients Details</p>
										</div>
										<div class="col-md-4 ms-auto  m-3 d-flex pt-3">
				        					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="search-input">
				        					<button class="btn btn-outline-success" type="submit" id="search-button">Search</button>
										</div>
									</div>
									
									<div class="card-body">
										<table class="table table-striped table-hover" id="data-table">
										 	<thead>
												<tr>
													<th>Id</th>
													<th>Disease</th>
													<th>Appointment Date</th>
													<th>Status</th>
													<th>Patient History</th>
												</tr>
											</thead>
											<tbody>
												<%
											
												List<PatientHistory> patientHistory = patientDao.getPatientHistory(patient_id);
												for(PatientHistory ph : patientHistory){
												%>
												<tr>
													<td><%=ph.getId() %></td>
													<td><%=ph.getDisease()%></td>
													<td><%=ph.getAppointment_date() %></td>
													<td><%=ph.getStatus() %></td>	
													<td><a id="detailBtn" class="nav-link text-white fs-5 btn btn-success col-md-4 open-modal" onclick="getPrescription(<%=ph.getId()%>)">Details</a></td>
												</tr>
												<%} %>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
				      </div>
				    </div>
				  </div>
				</section>
			 
		
		
	

	<!-- The modal -->
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
	            url: 'GetPrescription', // -> here it fails.............
	            data: { patientDetailId: patientDetailId},
	            success: function(response) {
	              console.log(response.length); 
	              $('#tableBody').empty();
	              for(var i = 0; i < response.length; i++){
	                  // Append a new row to the table with data from the response
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