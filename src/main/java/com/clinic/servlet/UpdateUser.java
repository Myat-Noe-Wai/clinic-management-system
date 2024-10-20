package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.models.User;
import com.clinic.dao.UserDAO;
import com.clinic.db.DBConnect;

public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("userId"));
			String name = request.getParameter("name");
			String role = request.getParameter("role").toLowerCase();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String qualification = request.getParameter("qualification");
			String specialist = request.getParameter("specialist");
			User user = new User(id, name, role, email, password, address, phone, qualification, specialist);
			UserDAO userDAO = new UserDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(userDAO.updateUser(user)) {
				session.setAttribute("sucMsg", "User updated Successfully!");
				response.sendRedirect("view_users.jsp");
			}else {
				session.setAttribute("errMsg", "Updating user failed!");
				response.sendRedirect("view_users.jsp");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
