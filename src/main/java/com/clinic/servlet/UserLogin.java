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
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnect.getConnection();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			UserDAO userDao = new UserDAO(DBConnect.getConnection());
			User user = userDao.UserLogin(email,password);
			HttpSession session = request.getSession();
			if(user == null) {
				session.setAttribute("errMsg", "invalid email & password");
				response.sendRedirect("user_login.jsp");
			}else {
				session.setAttribute("userObj", user);
				if(user.getRole().equals("admin") ) {
					response.sendRedirect("admin_home.jsp");
				}else {
					response.sendRedirect("doctor_home.jsp");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
