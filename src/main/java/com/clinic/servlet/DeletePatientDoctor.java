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
 * Servlet implementation class DeletePatientDoctor
 */
public class DeletePatientDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePatientDoctor() {
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
			int patient_id = Integer.parseInt(request.getParameter("id"));			
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(patientDao.deletePatient(patient_id)) {
				session.setAttribute("sucMsg", "Patient is deleted successfully!");
				response.sendRedirect("view_patient_doctor.jsp");
			}else {
				session.setAttribute("errMsg", "Patient deletion failed!");
				response.sendRedirect("view_patient_doctor.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
