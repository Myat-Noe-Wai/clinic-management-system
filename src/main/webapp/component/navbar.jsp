<%@page import="com.clinic.models.User"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand text-white" href="#"><i class="fa-sharp fa-solid fa-house-chimney-medical"></i> Myitar Mon Clinic</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0 mr-3">
        <li class="nav-item ">
          <a class="nav-link active text-white fs-5" aria-current="page" href="admin_home.jsp">HOME	</a>
        </li>
        
        <%
        	if(session.getAttribute("userObj") != null){
        		User user = (User)session.getAttribute("userObj");
        %>
        	 <li class="nav-item">
          		<a class="nav-link text-white fs-5" href="create_patient.jsp">PATIENT</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link text-white fs-5" href="view_patients.jsp">VIEW PATIENT</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link text-white fs-5" href="patient_record.jsp">APPOINTMENT</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link text-white fs-5" href="view_appointments.jsp">VIEW APPOINTMENT</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link text-white fs-5" href="create_user.jsp">USER</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link text-white fs-5" href="view_users.jsp">VIEW USER</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link text-white fs-5" href="pharmaceuticals.jsp">Pharceuticals</a>
        	</li>
      </ul>
       <form class="d-flex">
        <div class="dropdown">
  			<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
		    <%=user.getName() %>
		  	</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			  <li><a class="dropdown-item fs-5" href="UserLogout">Logout</a></li>
			</ul>
		</div>
       
      </form>
       <%
        	}else{
        		/* response.sendRedirect("index.jsp"); */
        %>
        
       <%} %>
    </div>
  </div>
</nav>