package com.clinic.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.clinic.models.Patient;
import com.clinic.models.PatientDetails;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;


public class CheckPatientHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckPatientHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer patientId = Integer.parseInt(request.getParameter("patientId"));
			
			PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
			boolean p = patientDAO.checkPatientHistory(patientId);
			
			if(p) {
				response.setContentType("application/json");
				response.getWriter().write(String.valueOf(p));	
			}else {
				System.out.print("not already data");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
