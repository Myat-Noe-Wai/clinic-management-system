package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.clinic.models.Pharmaceutical;
import com.clinic.models.Prescription;
import com.clinic.dao.PatientDAO;
import com.clinic.dao.PharmaceuticalDAO;
import com.clinic.db.DBConnect;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetPharmaceutical
 */
public class GetPharmaceutical extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPharmaceutical() {
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
			int pharId = Integer.parseInt(request.getParameter("pharId").trim());
			PharmaceuticalDAO pharDao = new PharmaceuticalDAO(DBConnect.getConnection());
			Pharmaceutical phar = pharDao.getPharmaceutical(pharId);			
			Gson gson = new Gson();
			String jsonPhar = gson.toJson(phar);
			response.setContentType("application/json");
			response.getWriter().write(jsonPhar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
