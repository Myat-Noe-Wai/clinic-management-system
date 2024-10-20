package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.clinic.models.PatientDetails;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class AddPatientRecord
 */
public class AddPatientRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddPatientRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			String appointmentDate = request.getParameter("appointmentDate");
			String disease = request.getParameter("disease");
			
			PatientDetails pDetails = new PatientDetails(patientId, disease, appointmentDate, "Pending", null); 
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();

				if(patientDao.addPatientHistory(pDetails)) {
					session.setAttribute("sucMsg", "Patient registered Successfully!");
					response.sendRedirect("patient_record.jsp");		
				}else {
					session.setAttribute("errMsg", "Patient register failed!");
					response.sendRedirect("patient_record.jsp");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
