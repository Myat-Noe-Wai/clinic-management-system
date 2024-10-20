<%@page import="com.clinic.models.Patient"%>
<%@page import="com.clinic.models.Pharmaceutical" %>
<%@page import="java.util.List"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PatientDAO"%>
<%@page import="com.clinic.dao.PharmaceuticalDAO" %>
<%@page import="com.clinic.models.PatientDetails"%>
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
<script>
		function checkMedicine(){
			var quantity = document.getElementById("quantity").value;
            var instruction = document.getElementById("instruction").value;
            
            if(quantity == "" || instruction == ""){
            	alert("Please fill the quantity or instruction field");
            }
            else{
            	addMedicine();
            }
		}
		
        function addMedicine() {
            var medicineSelect = document.getElementById("medicine");
            var medicine = medicineSelect.options[medicineSelect.selectedIndex].text;
            var medicineId = medicineSelect.options[medicineSelect.selectedIndex].value;
            var quantity = document.getElementById("quantity").value;
            var instruction = document.getElementById("instruction").value;                        

         	// Create a new row in the table
            var table = document.getElementById("medicineTable");
            var row = table.insertRow(-1);

            // Add cells to the row
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);

            // Set the innerHTML of the cells
            cell1.innerHTML = medicineSelect.options[medicineSelect.selectedIndex].text;
            cell2.innerHTML = quantity;
            cell3.innerHTML = instruction;
            
            row.setAttribute("data-medicineId", medicineId);
            
            medicineSelect.selectedIndex = 0;
            
            document.getElementById("instruction").value = "";            
            document.getElementById("quantity").value = "";
        }       
        
        function submitDataToServlet() {
            // Extract data from the table and prepare an array
            var medicineData = [];
            var table = document.getElementById("medicineTable");
            for (var i = 1; i < table.rows.length; i++) { // Start from 1 to skip header row
                var rowData = {
                    medicine: table.rows[i].cells[0].innerHTML,                    
                    quantity: table.rows[i].cells[1].innerHTML,
                    instruction: table.rows[i].cells[2].innerHTML,
                    medicineId: table.rows[i].getAttribute("data-medicineId")
                };
                medicineData.push(rowData);
            }

            var patientDetailId = document.getElementById("patientDetailId").value;
            var patientId = document.getElementById("patientId").value;

            $.ajax({
                type: "POST",
                url: "AddPrescription",
                contentType: "application/json",
                data: JSON.stringify({
                	patientId: patientId,
                    patientDetailId: patientDetailId,
                    medicineData: medicineData
                }),
                success: function(response) {
                	window.location.href = 'view_appointment_doctor.jsp';
                },
                error: function(error) {
                    console.error(error);
                }
            });
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
	<div class="container-fluid">
		<div class="row m-4">
			<div class="col-lg-12">
				<div class="card card-style">
					<div class="card-body">
					<p class="fs-3 text-center">Add Prescription to Patient</p>						
						<%
							String patient_id = request.getParameter("patientId");
							String patient_detail_id = request.getParameter("pDetailId");
							String pName = request.getParameter("pname");
							String disease = request.getParameter("disease");
							
							PharmaceuticalDAO pharmaDao = new PharmaceuticalDAO(DBConnect.getConnection());
							List<Pharmaceutical> pharmas = null;									
							pharmas = pharmaDao.getAllPharceuticals();																						
						%>
						<form class="row g-3 p-4" style="margin-top: -40px;">
							<div class="col-md-3" style="border-right: 2px solid gray !important;">
								<div class="row mb-3">
									<div class="col-md-5">
										<label class="form-label">Patient Name :</label>
									</div>
									<div class="col-md-5">
										<label class="form-label"><%=pName %></label>
									</div>
								</div>
								<div class="row mb-3">
									<div class="col-md-5">
										<label class="form-label">Disease :</label>
									</div>
									<div class="col-md-5">
										<label class="form-label"><%=disease %></label>
									</div>
								</div>
							</div>
							<div class="col-md-9">							
								<div class="row mb-3">
									<table class="table ms-2 table-striped table-hover" id="medicineTable">
		                                <thead>
		                                    <tr class="table-primary">
		                                        <th>Medicine</th>
		                                        <th>Quantity</th>
		                                        <th>Instruction</th>
		                                    </tr>
		                                </thead>
		                            </table>
								</div>
								<div class="row mb-3">
									<div class="col-md-2">
										<label class="form-label">Select Medicine</label>
									</div>
									<div class="col-md-2">
										<select id="medicine" name="medicine" class="form-select">
											<% for(Pharmaceutical pharma : pharmas){ %>
											<option value="<%=pharma.getId()%>"><%=pharma.getName() %></option>
											<% } %>
										</select>
									</div>
									<div class="col-md-2">
										<label class="form-label">Select Quantity</label>
									</div>
									<div class="col-md-2">										
										<input type="text" id="quantity" class="form-control">
									</div>
									<div class="col-md-1">
										<label class="form-label">Instruction</label>
									</div>
									<div class="col-md-3">
										<input type="hidden" value="<%=patient_id %>" id="patientId">
										<input type="hidden" value="<%=patient_detail_id %>" id="patientDetailId">
										<textarea class="form-control" id="instruction" name="instruction" style="height: 200px;"></textarea>
									</div>
								</div>
								<div class="row mb-3">								
									<div class="col-md-5">
										<button type="button" class="btn btn-success" onclick="checkMedicine()"><i class="fa-solid fa-plus"></i> Add Medicine</button>
									</div>	                            
								</div>
							</div>							
							<div class="row mb-3 mt-4">
								<div class="col-md-6 text-center ms-5">									
									<a href="view_appointment_doctor.jsp" class="btn btn-danger" style="margin-left: 350px;">Cancel</a>
								</div>
								<div class="col-md-5 ms-3">
									<!-- <button type="submit" class="btn btn-success ms-5">Add Prescription</button> -->
									<button type="button" class="btn btn-success" onclick="submitDataToServlet()">Add Prescription</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>