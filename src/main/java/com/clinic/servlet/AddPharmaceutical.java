package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.models.Pharmaceutical;
import com.clinic.models.User;
import com.clinic.dao.PharmaceuticalDAO;
import com.clinic.dao.UserDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class AddPharmaceutical
 */
public class AddPharmaceutical extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPharmaceutical() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String vendor = request.getParameter("vendor");
			String category = request.getParameter("category");
			int qty = Integer.parseInt(request.getParameter("qty"));
			Pharmaceutical phar = new Pharmaceutical(name, vendor, category, qty);
			PharmaceuticalDAO pharDao = new PharmaceuticalDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(pharDao.addPharmaceutical(phar)) {
				session.setAttribute("sucMsg", "Pharmaceutical is added Successfully!");
				response.sendRedirect("pharmaceuticals.jsp");
			}else {
				session.setAttribute("errMsg", "Adding pharmaceutical failed!");
				response.sendRedirect("add_pharmaceuticals.jsp");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
