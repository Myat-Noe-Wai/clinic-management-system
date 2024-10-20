package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.clinic.models.Pharmaceutical;
import com.clinic.models.Prescription;
import com.clinic.dao.PatientDAO;
import com.clinic.dao.PharmaceuticalDAO;
import com.clinic.db.DBConnect;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Servlet implementation class AddPrescription
 */
public class AddPrescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPrescription() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			int patientId = Integer.parseInt(request.getParameter("patientId"));
//			int patientDetailId = Integer.parseInt(request.getParameter("patientDetailId"));
//			String medicine = request.getParameter("medicine");
//			String instruction = request.getParameter("instruction");
//			int quantity = Integer.parseInt(request.getParameter("quantity"));
//			Prescription ps = new Prescription(patientDetailId, medicine, quantity, instruction);
//			PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
//			HttpSession session = request.getSession();
//			if(patientDao.addPrescription(ps)) {
//				patientDao.updateStatus(patientId);
//				session.setAttribute("sucMsg", "Prescription added successfully!");
//				response.sendRedirect("view_appointment_doctor.jsp");
//			}else {
//				session.setAttribute("errMsg", "Adding Prescription failed!");
//				response.sendRedirect("view_appointment_doctor.jsp");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
	            BufferedReader reader = request.getReader();
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, Object> jsonData = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});
	            
	            int patientId = Integer.parseInt((String) jsonData.get("patientId"));
	            int patientDetailId = Integer.parseInt((String) jsonData.get("patientDetailId"));
	            List<Map<String, Object>> medicineData = (List<Map<String, Object>>) jsonData.get("medicineData");
	            
	            PatientDAO patientDao = new PatientDAO(DBConnect.getConnection());
	            PharmaceuticalDAO pharDao = new PharmaceuticalDAO(DBConnect.getConnection());
	            HttpSession session = request.getSession();
	            boolean status = false;
	            for (Map<String, Object> prescriptionData : medicineData) {
	                String medicine = (String) prescriptionData.get("medicine");
	                int medicineId = Integer.parseInt((String) prescriptionData.get("medicineId"));
	                String instruction = (String) prescriptionData.get("instruction");
	                int quantity = Integer.parseInt((String) prescriptionData.get("quantity"));
	                Prescription ps = new Prescription(patientDetailId, medicine, quantity, instruction);
	                
	                Pharmaceutical phar = pharDao.getPharmaceutical(medicineId);
	                int medicineQty = (phar.getQty() - quantity);
	                Pharmaceutical pharma = new Pharmaceutical(medicineId, medicineQty);
	                
	                if (patientDao.addPrescription(ps) && pharDao.subtractPharmaceutical(pharma)) {
	                	status = true;
	                }
	            }
	            if(status) {
	            	patientDao.updateStatus(patientId);
	            	session.setAttribute("sucMsg", "Prescription added successfully!");  
	            }
	            else {
	            	session.setAttribute("errMsg", "Adding Prescription failed!");
	            }
            } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
