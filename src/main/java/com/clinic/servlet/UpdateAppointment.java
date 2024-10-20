package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.models.PatientDetails;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class UpdateAppointment
 */
public class UpdateAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAppointment() {
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
			
			int id = Integer.parseInt(request.getParameter("patientDetailId"));
			String appointmentDate = request.getParameter("appointmentDate");
			String disease = request.getParameter("disease");
			PatientDetails pDetails = new PatientDetails(id, id, disease, appointmentDate, disease, null) ;
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			
			if(patientDao.updatePatientHistory(pDetails)) {
				System.out.println("success to update patientDetails");
				session.setAttribute("sucMsg", "Update patientDetails Successfully!");
				response.sendRedirect("view_appointments.jsp");
				
			}else {
				System.out.println("failed");
				session.setAttribute("errMsg", "updating patientDetails failed!");
				response.sendRedirect("view_appointments.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
