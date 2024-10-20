package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.dao.DoctorDAO;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class DeleteAppointment
 */
public class DeleteAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	Integer patientDetailId = Integer.parseInt(request.getParameter("patientDetailId"));
			
			PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(patientDAO.deleteAppointment(patientDetailId)) {
				session.setAttribute("sucMsg", "Appointment deleted Successfully!");
				response.sendRedirect("view_appointments.jsp");
			}else {
				session.setAttribute("errMsg", "Deleting appointment failed!");
				response.sendRedirect("view_appointments.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
