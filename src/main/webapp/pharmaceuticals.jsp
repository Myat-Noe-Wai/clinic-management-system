<%@page import="com.clinic.models.Pharmaceutical"%>
<%@page import="java.util.List"%>
<%@page import="com.clinic.db.DBConnect"%>
<%@page import="com.clinic.dao.PharmaceuticalDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<%@ include file="component/style.jsp"%>
<script type="text/javascript">    
    function prepareDelete(pharId) {
        document.getElementById("deleteId").value = pharId;
        var status = confirmDelete();
        console.log(status);
        if (status) {
            document.getElementById("deleteForm").submit();
        }       
    }
    function confirmDelete() {
        return confirm("Are you sure you want to delete this patient?");
    }
</script>
</head>
<body>
	<%@ include file="component/navbar.jsp"%>
	<%
		if(session.getAttribute("userObj") == null){	
			response.sendRedirect("user_login.jsp");
		}
	%>
	<div class="container-fulid p-5">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-style">
					<p class="fs-3 text-center mt-3">Pharceuticals Inventory</p>

					<div class="row" style="margin-top: -45px;">
						<div class="col-md-6">
							<a href="add_pharmaceuticals.jsp" class="btn btn-success" style="margin-top: 32px; margin-left: 16px;">Add Pharmaceuticals</a>
						</div>
						<div class="col-md-6">
							<div class="col-md-4 ms-auto  m-3 d-flex pt-3">
								<input class="form-control me-2" type="search"
									placeholder="Search" aria-label="Search" id="search-input">
								<button class="btn btn-outline-success" type="submit" id="search-button">Search</button>
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
						<form action="DeletePharmaceutical" method="post" id="deleteForm">
							<input type="hidden" id="deleteId" name="id" value="">
							<table class="table table-striped table-hover" id="data-table">
								<tr class="table-primary">
									<th>No</th>
									<th>Pharceutical Name</th>
									<th>Pharceutical Vendor</th>
									<th>Pharceutical Category</th>
									<th>Pharceutical Quantity</th>
									<th>Action</th>
								</tr>
								<%
									PharmaceuticalDAO pharmaDao = new PharmaceuticalDAO(DBConnect.getConnection());
									List<Pharmaceutical> pharmas = null;									
									pharmas = pharmaDao.getAllPharceuticals();									
									
									for(Pharmaceutical pharma : pharmas){
								%>
								<tr>
									<td><%=pharma.getId() %></td>
									<td><%=pharma.getName() %></td>
									<td><%=pharma.getVendor() %></td>
									<td><%=pharma.getCategory() %></td>
									<td><%=pharma.getQty() %></td>
									<td>
										<a class="btn btn-secondary" onclick="getPharmaceutical(<%=pharma.getId() %>)"><i class="fa-regular fa-eye"></i> View</a>
										<a href="edit_pharmaceuticals.jsp?id=<%=pharma.getId() %>"class="btn btn-primary"><i class="fa-solid fa-pen-to-square"></i> Edit</a>
										<button type="button" class="btn btn-danger" onclick="prepareDelete(<%=pharma.getId() %>);"><i class="fa-solid fa-trash-can"></i> Delete</button>
									</td>
								</tr>
								<% 
									}
								%>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="dataModal" tabindex="-1" aria-labelledby="dataModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="dataModalLabel">Pharmaceutical Details</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">	                
	                <table id="tableId" class="table">
	                	<thead>
	                		<tr>
	                			<th>Id</th>
	                			<th>Pharceutical Name</th>
								<th>Pharceutical Vendor</th>
								<th>Pharceutical Category</th>
								<th>Pharceutical Quantity</th>
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
		function getPharmaceutical(pharId){
		  $.ajax({
	            type: 'POST',
	            url: 'GetPharmaceutical',
	            data: { pharId : pharId},
	            success: function(response) {
	              console.log(response.id); 
	              $('#tableBody').empty();	              
	              $('#tableBody').append('<tr>' +
	                 '<td>' + response.id + '</td>' +
	                 '<td>' + response.name + '</td>' +
	                 '<td>' + response.vendor + '</td>' +
	                 '<td>' + response.category + '</td>' +
	                 '<td>' + response.qty + '</td>' +
	                 '</tr>');	              
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
	            td = tr[i].getElementsByTagName("td")[1];
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