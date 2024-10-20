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

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String specialist = request.getParameter("specialist");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String qualification = request.getParameter("qualification");
			String role = request.getParameter("role").toLowerCase();
			User user = new User(name, role, email, password, address, phone, qualification, specialist);
			UserDAO userDao = new UserDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(userDao.addUser(user)) {
				session.setAttribute("sucMsg", "User registered Successfully!");
				response.sendRedirect("create_user.jsp");
			}else {
				session.setAttribute("errMsg", "User register failed!");
				response.sendRedirect("create_user.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
