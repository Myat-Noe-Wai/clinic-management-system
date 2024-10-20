package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.awt.Window;
import java.io.IOException;

import com.clinic.dao.DoctorDAO;
import com.clinic.dao.UserDAO;
import com.clinic.db.DBConnect;

public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("userId"));
			
			UserDAO userDAO = new UserDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(userDAO.deleteUser(id)) {
				session.setAttribute("sucMsg", "User deleted Successfully!");
				response.sendRedirect("view_users.jsp");
			}else {
				session.setAttribute("errMsg", "Deleting user failed!");
				response.sendRedirect("view_users.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
