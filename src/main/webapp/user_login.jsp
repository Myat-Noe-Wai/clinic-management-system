<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<%@ include file="component/style.jsp" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ page isELIgnored = "false" %>
</head>
<body>
	<%@ include file="component/navbar.jsp" %>
	
	<div class="container p-5 ">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card card-style">
				  <div class="card-body">
				 <%--  <c:if test="${not empty errMsg }">
				  	<p class="text-center text-success fs-3">${errMsg }</p>
				  	<c:remove var="errMsg" scope="session"/>
				  </c:if> --%>
				  <h2 class="text-center text-primary">User Login</h2>
				  	<%
				  		session.removeAttribute("userObj");
						if(session.getAttribute("errMsg")!=null){
					%>
						<p class="text-warning"><%= session.getAttribute("errMsg") %></p>
					<%
						session.removeAttribute("errMsg");
					}
					%>
					<%
						if(session.getAttribute("sucMsg")!=null){
					%>
						<p class="text-success"><%= session.getAttribute("sucMsg") %></p>
					<%
						session.removeAttribute("sucMsg");
					}
					%>
					
				    <form method="POST" action="UserLogin">
					  <div class="mb-3">
					    <label for="exampleInputEmail1" class="form-label">Email address</label>
					    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" required>
					    <div id="emailHelp" class="form-text">Please Enter Your Email Address!</div>
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputPassword1" class="form-label">Password</label>
					    <input type="password" class="form-control" id="exampleInputPassword1" name="password" required>
					  </div>
					  <button type="submit" class="btn btn-primary col-md-12 mb-5 mt-3">Login</button>
					</form>
				  </div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>