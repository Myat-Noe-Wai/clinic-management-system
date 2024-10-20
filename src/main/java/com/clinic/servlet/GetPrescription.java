package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.clinic.models.Prescription;
import com.clinic.dao.PatientDAO;
import com.clinic.db.DBConnect;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetPrescription
 */
public class GetPrescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPrescription() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int patientDetailId = Integer.parseInt(request.getParameter("patientDetailId").trim());
			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
			List<Prescription> listPrescriptions = patientDao.getPrescription(patientDetailId);
			   Gson gson = new Gson();
			   String jsonPrescriptions = gson.toJson(listPrescriptions);
			response.setContentType("application/json");
			response.getWriter().write(jsonPrescriptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
