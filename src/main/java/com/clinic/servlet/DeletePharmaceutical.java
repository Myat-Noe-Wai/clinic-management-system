package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.dao.PatientDAO;
import com.clinic.dao.PharmaceuticalDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class DeletePharmaceutical
 */
public class DeletePharmaceutical extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePharmaceutical() {
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
			int pharId = Integer.parseInt(request.getParameter("id"));
			PharmaceuticalDAO pharDao = new PharmaceuticalDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(pharDao.deletePharmaceutical(pharId)) {
				session.setAttribute("sucMsg", "Pharmaceutical is deleted successfully!");
				response.sendRedirect("pharmaceuticals.jsp");
			}else {
				session.setAttribute("errMsg", "Pharmaceutical deletion failed!");
				response.sendRedirect("pharmaceuticals.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
