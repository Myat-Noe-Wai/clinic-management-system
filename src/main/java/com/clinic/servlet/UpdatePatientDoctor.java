package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.clinic.models.Patient;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;

/**
 * Servlet implementation class UpdatePatientDoctor
 */
public class UpdatePatientDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatientDoctor() {
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
			int id = Integer.parseInt(request.getParameter("patientId"));
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			int age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String phNo = request.getParameter("phoneNo");
			Patient p = new Patient(id, name, gender, age, address, phNo);
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			if(patientDao.updatePatient(p)) {
				session.setAttribute("sucMsg", "Update patient infomation successfully!");
				response.sendRedirect("view_patient_doctor.jsp");
			}else {
				session.setAttribute("errMsg", "Creating patient failed!");
				response.sendRedirect("edit_patient_doctor.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
