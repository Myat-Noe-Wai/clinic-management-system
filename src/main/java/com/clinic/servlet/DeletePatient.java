package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class DeletePatient
 */
public class DeletePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("patientId"));
			PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			
			if(patientDAO.deletePatient(id)) {
				session.setAttribute("sucMsg", " Patient deleted successfully!");
				response.sendRedirect("view_patients.jsp");
			}else {
				session.setAttribute("errMsg", "Deleting patient failed!");
				response.sendRedirect("view_patients.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
