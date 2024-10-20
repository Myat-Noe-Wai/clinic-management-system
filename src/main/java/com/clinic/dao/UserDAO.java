package com.clinic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.clinic.models.Doctor;
import com.clinic.models.User;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}


	public User UserLogin(String email,String password) {
		User user = null;
		try {
			String sql = "select * from user where email = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setRole(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setAddress(rs.getString(6));
				user.setPhone_no(rs.getString(7));
				user.setQualification(rs.getString(8));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean addUser(User user) {
		boolean b = false;
		try {
			String sql = "insert into user(name,role,email,password,address,phone_no,qualification,specialist) value(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getRole());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getPhone_no());
			ps.setString(7, user.getQualification());
			ps.setString(8, user.getSpecialist());
			int i = ps.executeUpdate();
			if(i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	public List<User> getAllUsers(){
		List<User> listUser = new ArrayList<User>();
		User user = null;
		try {
			
			String sql = "select * from user";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setRole(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setAddress(rs.getString(6));
				user.setPhone_no(rs.getString(7));
				user.setQualification(rs.getString(8));
				user.setSpecialist(rs.getString(9));
				listUser.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}
	
	public User getUserById(Integer id){
		User user = null;
		try {
			
			String sql = "select * from user where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setRole(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
				user.setAddress(rs.getString(6));
				user.setPhone_no(rs.getString(7));
				user.setQualification(rs.getString(8));
				user.setSpecialist(rs.getString(9));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean updateUser(User user) {
		boolean b = false;
		try {
			String sql = "update user set name=?,role = ?,email = ?,password = ?,address = ?,phone_no = ?,qualification = ?,specialist = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getRole());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getPhone_no());
			ps.setString(7, user.getQualification());
			ps.setString(8, user.getSpecialist());
			ps.setInt(9, user.getId());
			int i = ps.executeUpdate();
			if(i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean deleteUser(Integer id) {
		boolean b = false;
		try {
			String sql = "delete from user where id = ?";
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
	
	public Integer getDoctorCount(){
		Integer count = 0;
			try {
				
				String sql = "select * from user where role= ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "doctor");
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
}
