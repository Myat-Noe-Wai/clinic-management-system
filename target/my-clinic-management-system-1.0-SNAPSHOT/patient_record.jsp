<%@page import="java.util.ArrayList"%>
<%@page import="com.clinic.models.Prescription"%>
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
<title>Add Patient Record</title>
<%@ include file="component/style.jsp" %>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	<%
		if(session.getAttribute("userObj") == null){
	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container p-5 ">
	<form  id="myForm" action="AddPatientRecord" method="post">
		<div class="mb-3">
			<h3 class="text-center mb-5"><label for="inputPassword4" class="form-label">Add Patient's Appointment</label></h3>
			 
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
			<select required class="form-control" name="patientId" id="patientId">
				<option value="">--Select Patient Id--</option>
				<%
					PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
					List<Patient> listPatient = patientDAO.getAllPatients();
					for (Patient p: listPatient) {
				%>
					<option value="<%=p.getId()%>"><%=p.getId()%>
					</option>
				<%
					}
				%>
			</select>
		</div>
		<div class="mb-3">
	    	<label  class="form-label">Patient Name</label>
	    	<input type="text" class="form-control" id="patientName" readonly>
	    
	  	</div>
	  <div class="mb-3">
	    <label for="exampleInputEmail1" class="form-label">Appointment Date</label>
	    <input type="date" class="form-control" name="appointmentDate" id="appointmentDate" required>
	    <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
	  </div>
	  <div class="mb-5">
	    <label for="exampleInputPassword1" class="form-label">Disease</label>
	    <input type="text" class="form-control" name="disease" id="disease" required>
	  </div>
	  <%
	  List<Prescription> listPrescription = new ArrayList<Prescription>();
	  %>

  		<button type="submit" class="btn btn-primary" onclick="checkPatientHistory(event)">Add Record</button>
	 </form>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Are you sure want to add?</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>This user has already exist. Do you want to add user?</p>
	      </div>
	      <div class="modal-footer">
	      	<form action="AddPatientRecord" method="post">
	      		<input type="hidden" name="patientId" id="patientId1">
	      		<input type="hidden" name="appointmentDate" id="appointmentDate1">
	      		<input type="hidden" name="disease" id="disease1">
	      		<a href="#" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</a>
	        	<button type="submit" class="btn btn-primary" >OK</button>
	      	</form>
	       
	      </div>
	    </div>
	  </div>
	</div>
	<script>
	function checkPatientHistory(event){
		 event.preventDefault();
		var patientId = document.getElementById("patientId").value;
		/* var appointDate = document.getElementById("appointmentDate").value;
		var disease = document.getElementById("disease").value; */
		
		 $.ajax({
             type: "POST",
             url: "CheckPatientHistory",
             data: {
            	 "patientId": patientId
             },
             success: function(response){

                 let f = "true"
                 if(response === true){
                	 var patientId = document.getElementById("patientId").value;
                	 var appointmentDate = document.getElementById("appointmentDate").value;
                	 var disease = document.getElementById("disease").value;
                	 
                	 document.getElementById("patientId1").value = patientId;
                	 document.getElementById("appointmentDate1").value = appointmentDate;
                	 document.getElementById("disease1").value = disease;
                	 $('#confirmModal').modal('show');
                	 
                 }else{
                	 document.getElementById("myForm").submit();
                 }
                
             },
             error: function(xhr, status, error){
                 console.error("Error: " + error);
             }
         }); 
	}
	  function showPatientForm() {
          console.log(document.getElementById("medicine").value);
          var medicine = document.getElementById("medicine").value;
          
       <%--    <%String medicines =%>medicine;
          <%= medicines%> --%>
      }
	   $("#patientId").change(function(){
           var selectedValue = $(this).val();
           // Make an AJAX request to the server
            $.ajax({
               type: "POST",
               url: "GetPatientById",
               data: {"selectedValue": selectedValue},
               success: function(response){
                   // Handle the server response
                   document.getElementById("patientName").value = response["name"]; 
               },
               error: function(xhr, status, error){
                   console.error("Error: " + error);
               }
           }); 
       });
   
	</script>
</body>
</html>