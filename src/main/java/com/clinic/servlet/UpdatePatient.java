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
 * Servlet implementation class UpdatePatient
 */
public class UpdatePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("patientId"));
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			int age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String phoneNo = request.getParameter("phoneNo");
			Patient patient = new Patient(id,name, gender, age, address, phoneNo);
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			
			if(patientDao.updatePatient(patient)) {
				session.setAttribute("sucMsg", " Patient updated successfully!");
				response.sendRedirect("view_patients.jsp");
			}else {
				session.setAttribute("errMsg", "Updating patient failed!");
				response.sendRedirect("view_patients.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
