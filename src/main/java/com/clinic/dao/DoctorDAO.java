package com.clinic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.clinic.models.Doctor;

public class DoctorDAO {
	Connection conn;

	public DoctorDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	public boolean createDoctor(Doctor doctor) {
		boolean b = false;
		try {
			String sql = "insert into doctor(name,email,password,qualification,specialist,phone_no,address) value(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getEmail());
			ps.setString(3, doctor.getPassword());
			ps.setString(4, doctor.getQualification());
			ps.setString(5, doctor.getSpecialist());
			ps.setString(6, doctor.getPhone_no());
			ps.setString(7, doctor.getAddress());
			int i = ps.executeUpdate();
			if(i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public List<Doctor> getAllDoctors(){
		List<Doctor> listDoctor = new ArrayList<Doctor>();
		Doctor doctor = null;
		try {
			
			String sql = "select * from doctor";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setName(rs.getString(2));
				doctor.setEmail(rs.getString(3));
				doctor.setPassword(rs.getString(4));
				doctor.setQualification(rs.getString(5));
				doctor.setSpecialist(rs.getString(6));
				doctor.setPhone_no(rs.getString(7));
				doctor.setAddress(rs.getString(8));
				listDoctor.add(doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDoctor;
	}
	
	public Integer getDoctorCount(){
	Integer count = 0;
		try {
			
			String sql = "select * from doctor";
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
	
	public Doctor getDoctorById(Integer id){
		Doctor doctor = null;
		try {
			
			String sql = "select * from doctor where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setName(rs.getString(2));
				doctor.setEmail(rs.getString(3));
				doctor.setPassword(rs.getString(4));
				doctor.setQualification(rs.getString(5));
				doctor.setSpecialist(rs.getString(6));
				doctor.setPhone_no(rs.getString(7));
				doctor.setAddress(rs.getString(8));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}
	
	public boolean updateDoctor(Doctor doctor) {
		boolean b = false;
		try {
			String sql = "update doctor set name=?,email=?,password=?,qualification=?,specialist=?,phone_no=?,address=? where id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getEmail());
			ps.setString(3, doctor.getPassword());
			ps.setString(4, doctor.getQualification());
			ps.setString(5, doctor.getSpecialist());
			ps.setString(6, doctor.getPhone_no());
			ps.setString(7, doctor.getAddress());
			ps.setInt(8, doctor.getId());
			int i = ps.executeUpdate();
			if(i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean deleteDoctor(Integer id) {
		boolean b = false;
		try {
			String sql = "delete from doctor where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if(i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
}
