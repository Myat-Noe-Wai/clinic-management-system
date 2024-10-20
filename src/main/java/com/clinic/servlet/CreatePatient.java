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

public class CreatePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreatePatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			int age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");
			String phoneNo = request.getParameter("phoneNo");
			Patient patient = new Patient(name, gender, age, address, phoneNo);
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			HttpSession session = request.getSession();
			
			if(patientDao.createPatient(patient)) {
				session.setAttribute("sucMsg", "Created patient successfully!");
				response.sendRedirect("create_patient.jsp");
			}else {
				session.setAttribute("errMsg", "Creating patient failed!");
				response.sendRedirect("create_patient.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
