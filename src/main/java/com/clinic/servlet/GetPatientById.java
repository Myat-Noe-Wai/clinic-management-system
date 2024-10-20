package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.clinic.models.Patient;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetPatientById
 */
public class GetPatientById extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetPatientById() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			  Integer patientId = Integer.parseInt(request.getParameter("selectedValue"));
			  PatientDAO patientDAO = new PatientDAO(DBConnect.getConnection());
			  Patient patient = patientDAO.getPatientById(patientId);
			  
			  Gson gson = new Gson();
			  String jsongPatient = gson.toJson(patient);
			  
			  response.setContentType("application/json");
				response.getWriter().write(jsongPatient);
			  
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
