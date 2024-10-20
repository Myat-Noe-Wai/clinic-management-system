package com.clinic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.clinic.models.Patient;
import com.clinic.models.PatientDetails;
import com.clinic.models.PatientHistory;
import com.clinic.models.Prescription;

public class PatientDAO {
	private Connection conn;

	public PatientDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	public boolean createPatient(Patient patient) {
		boolean f = false;
		try {
			String sql = "insert into patient(name,gender,age,address,phone_no) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, patient.getName());
			ps.setString(2, patient.getGender());
			ps.setInt(3, patient.getAge());
			ps.setString(4, patient.getAddress());
			ps.setString(5, patient.getPhoneNo());
			int i = ps.executeUpdate();
			System.out.println("boolean"+ i);
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<Patient> getAllPatients(){
		List<Patient> patients = new ArrayList<Patient>();
		Patient patient = null;
		try {
			String sql = "select * from patient";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				patient = new Patient();
				patient.setId(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setGender(rs.getString(3));
				patient.setAge(rs.getInt(4));
				patient.setAddress(rs.getString(5));
				patient.setPhoneNo(rs.getString(6));
				patients.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	public Integer getPatientCount(){
		int count = 0;

		try {
			String sql = "select * from patient";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Integer getAppointmentCount(){
		int count = 0;

		try {
			String sql = "select * from patient_details where status= ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "pending");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<PatientHistory> getPatientHistory(int patient_id){
		List<PatientHistory> listPHistory = new ArrayList<PatientHistory>();
		PatientHistory pHistory = null;
		try {
			String sql = "select * from patient_details where patient_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, patient_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pHistory = new PatientHistory();
				pHistory.setId(rs.getInt(1));
				pHistory.setPatient_id(rs.getInt(2));
				pHistory.setDisease(rs.getString(3));
				pHistory.setAppointment_date(rs.getDate(4));
				pHistory.setStatus(rs.getString(5));
				listPHistory.add(pHistory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPHistory;
	}
	
	public List<Prescription> getPrescription(int patient_detail_id){
		List<Prescription> listPrescription = new ArrayList<Prescription>();
		Prescription prescription = null;
		try {
			String sql = "select * from prescription where patient_details_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, patient_detail_id);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ) {
				prescription = new Prescription();
				prescription.setId(rs.getInt(1));
				prescription.setPatient_details_id(rs.getInt(2));
				prescription.setMedicine(rs.getString(3));
				prescription.setQuantity(rs.getInt(4));
				prescription.setInstruction(rs.getString(5));
				listPrescription.add(prescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPrescription;
	}
	
	public List<PatientHistory> getPatientDetail(int patient_id){
		List<PatientHistory> patientHistory = new ArrayList<PatientHistory>();
		return patientHistory;
	}
	
	public boolean checkPatientHistory(Integer patientId) {
		boolean f = false;
		try {
			String sql = "select * from patient_details where patient_id = ? and status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, patientId);
			ps.setString(2, "Pending");
			ResultSet rs = ps.executeQuery();
			Integer count = 0;
			while(rs.next()) {
				count++;
				f = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean addPatientHistory(PatientDetails patientDetails) {
		boolean f = false;
		try {
			String sql = "insert into patient_details (patient_id,disease,appointment_date,status) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1	, patientDetails.getPatient_id());
			ps.setString(2	, patientDetails.getDisease());
			ps.setString(3	,  patientDetails.getAppointment_date());
			ps.setString(4	, "Pending");
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean addPrescription(Prescription prescription) {
		boolean f = false;
		try {
			String sql = "insert into prescription (patient_details_id,medicine,quantity,instruction) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1	, prescription.getPatient_details_id());
			ps.setString(2	, prescription.getMedicine());
			ps.setInt(3	,  prescription.getQuantity());
			ps.setString(4	, prescription.getInstruction());
			
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public Patient getPatientById(Integer id){
		
		Patient patient = null;
		try {
			String sql = "select * from patient where id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				patient = new Patient();
				patient.setId(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setGender(rs.getString(3));
				patient.setAge(rs.getInt(4));
				patient.setAddress(rs.getString(5));
				patient.setPhoneNo(rs.getString(6));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient;
	}
	
	public boolean updatePatient(Patient patient) {
		boolean f = false;
		try {
			String sql = "update  patient set name=?,gender=?,age=?,address=?,phone_no=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, patient.getName());
			ps.setString(2, patient.getGender());
			ps.setInt(3, patient.getAge());
			ps.setString(4, patient.getAddress());
			ps.setString(5, patient.getPhoneNo());
			ps.setInt(6, patient.getId());
			int i = ps.executeUpdate();
			System.out.println("boolean"+ i);
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean deletePatient(Integer id) {
		boolean f = false;
		try {
			String sql = "delete from patient where id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
		
			int i = ps.executeUpdate();
			System.out.println("boolean"+ i);
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<PatientDetails> getPatientDetailsById(int id) {
		PatientDetails pd = null;
		List<PatientDetails> pdList = new ArrayList<PatientDetails>();
		
		try {
			String sql = "select * from patient_details where patient_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pd = new PatientDetails();
				pd.setId(rs.getInt(1));
				pd.setPatient_id(rs.getInt(2));
				pd.setDisease(rs.getString(3));
				pd.setAppointment_date(rs.getString(4));
				pd.setStatus(rs.getString(5));
				pdList.add(pd);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return pdList;
	}
	
	public List<Patient> getAllPendingPatients(){
		List<Patient> patients = new ArrayList<Patient>();
		Patient patient = null;
		try {
			String sql = "SELECT p.*, pd.disease, pd.appointment_date, pd.status FROM patient p INNER JOIN patient_details pd ON p.id = pd.patient_id WHERE pd.status = 'Pending'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				patient = new Patient();
				patient.setId(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setGender(rs.getString(3));
				patient.setAge(rs.getInt(4));
				patient.setAddress(rs.getString(5));
				patient.setPhoneNo(rs.getString(6));
				patients.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	public List<PatientDetails> getAllPendingPatientDetails(){
	    PatientDetails pd = null;
	    List<PatientDetails> patientsDetails = new ArrayList<PatientDetails>();
	    try {
	        String sql = "SELECT pd.id, pd.patient_id, pd.disease, pd.appointment_date, pd.status FROM patient p INNER JOIN patient_details pd ON p.id = pd.patient_id WHERE pd.status = 'Pending'";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            pd = new PatientDetails();
	            pd.setId(rs.getInt(1));
	            pd.setPatient_id(rs.getInt(2));
	            pd.setDisease(rs.getString(3));
	            pd.setAppointment_date(rs.getString(4));
	            pd.setStatus(rs.getString(5));
	            patientsDetails.add(pd);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return patientsDetails;
	}
	
	public boolean updateStatus(int patientId) {
		boolean result = false;
		
		try {
			String sql = "update patient_details set Status='Done' where patient_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, patientId);
			int i = ps.executeUpdate();
			if (i == 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public boolean updatePatientHistory(PatientDetails patientDetails) {
		boolean f = false;
		System.out.println("id"+patientDetails.getId()+"/"+patientDetails.getDisease()+"/"+patientDetails.getAppointment_date());
		try {
			String sql = "update patient_details set disease=?,appointment_date=?  where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1	, patientDetails.getDisease());
			ps.setString(2	,  patientDetails.getAppointment_date());
			ps.setInt(3	, patientDetails.getId());
			System.out.println("addPatinetId");
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public boolean deleteAppointment(int id) {
		boolean f = false;
		
		try {
			String sql = "delete from patient_details where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
		
			ps.setInt(1	, id);
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public List<PatientDetails> getAllAppointment(){
		List<PatientDetails> listPatientDetails = new ArrayList<PatientDetails>();
		PatientDetails patientDetails = null;
		try {
			String sql = "select p.id,p.name,p.gender,p.address,p.age,p.phone_no,pd.id,pd.disease,pd.appointment_date from patient_details as pd join patient as p on pd.patient_id = p.id where pd.status= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Pending");
			ResultSet rs = ps.executeQuery();
			while( rs.next() ) {
				patientDetails = new PatientDetails();
				
				patientDetails.setPatient_id(rs.getInt(1));
				patientDetails.setName(rs.getString(2));
				patientDetails.setGender(rs.getString(3));
				patientDetails.setAddress(rs.getString(4));
				patientDetails.setAge(rs.getInt(5));
				patientDetails.setPhoneNo(rs.getString(6));
				patientDetails.setId(rs.getInt(7));
				patientDetails.setDisease(rs.getString(8));
				patientDetails.setAppointment_date(rs.getString(9));
				System.out.println("suc"+ rs.getString(2));
				listPatientDetails.add(patientDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPatientDetails;
	}
	
	public PatientDetails getAppointmentByPatientId(Integer id){
		System.out.println("patientDetail"+id);
		PatientDetails patientDetails = null;
		try {
			String sql = "select p.id,p.name,p.gender,p.address,p.age,p.phone_no,pd.id,pd.disease,pd.appointment_date from patient_details as pd join patient as p on pd.patient_id = p.id where pd.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ) {
				patientDetails = new PatientDetails();
				
				patientDetails.setPatient_id(rs.getInt(1));
				patientDetails.setName(rs.getString(2));
				patientDetails.setGender(rs.getString(3));
				patientDetails.setAddress(rs.getString(4));
				patientDetails.setAge(rs.getInt(5));
				patientDetails.setPhoneNo(rs.getString(6));
				patientDetails.setId(rs.getInt(7));
				patientDetails.setDisease(rs.getString(8));
				patientDetails.setAppointment_date(rs.getString(9));
				System.out.println("patientDetail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientDetails;
	} 
}
