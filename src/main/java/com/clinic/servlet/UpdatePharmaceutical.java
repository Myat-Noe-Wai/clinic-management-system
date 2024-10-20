package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.models.Patient;
import com.clinic.models.Pharmaceutical;
import com.clinic.dao.PatientDAO;
import com.clinic.dao.PharmaceuticalDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class UpdatePharmaceutical
 */
public class UpdatePharmaceutical extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePharmaceutical() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String vendor = request.getParameter("vendor");
			String category = request.getParameter("category");
			int qty = Integer.parseInt(request.getParameter("qty"));
			Pharmaceutical phar = new Pharmaceutical(id, name, vendor, category, qty);
			PharmaceuticalDAO pharDao = new PharmaceuticalDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(pharDao.updatePharmaceutical(phar)) {
				session.setAttribute("sucMsg", "Pharmaceutical infomation is updated successfully!");
				response.sendRedirect("pharmaceuticals.jsp");
			}else {
				session.setAttribute("errMsg", "Creating patient failed!");
				response.sendRedirect("edit_pharmaceuticals.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
