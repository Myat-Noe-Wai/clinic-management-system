package com.clinic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.clinic.models.Doctor;
import com.clinic.models.Patient;
import com.clinic.models.Pharmaceutical;

public class PharmaceuticalDAO {
	Connection conn;

	public PharmaceuticalDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addPharmaceutical(Pharmaceutical phar) {
		boolean b = false;
		try {
			String sql = "insert into pharmaceuticals(name, vendor, category, quantity) value(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phar.getName());
			ps.setString(2, phar.getVendor());
			ps.setString(3, phar.getCategory());
			ps.setInt(4, phar.getQty());
			int i = ps.executeUpdate();
			if(i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public List<Pharmaceutical> getAllPharceuticals(){
		List<Pharmaceutical> listPharmaceutical = new ArrayList<Pharmaceutical>();
		Pharmaceutical phar = null;
		try {
			
			String sql = "select * from pharmaceuticals";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				phar = new Pharmaceutical();
				phar.setId(rs.getInt(1));
				phar.setName(rs.getString(2));
				phar.setVendor(rs.getString(3));
				phar.setCategory(rs.getString(4));
				phar.setQty(rs.getInt(5));
				listPharmaceutical.add(phar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPharmaceutical;
	}
	
	public Pharmaceutical getPharmaceutical(int pharId){
		Pharmaceutical phar = null;
		try {
			
			String sql = "select * from pharmaceuticals where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pharId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				phar = new Pharmaceutical();
				phar.setId(rs.getInt(1));
				phar.setName(rs.getString(2));
				phar.setVendor(rs.getString(3));
				phar.setCategory(rs.getString(4));
				phar.setQty(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phar;
	}
	
	public boolean updatePharmaceutical(Pharmaceutical phar) {
		boolean f = false;
		try {
			String sql = "update pharmaceuticals set name=?, vendor=?, category=?, quantity=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, phar.getName());
			ps.setString(2, phar.getVendor());
			ps.setString(3, phar.getCategory());
			ps.setInt(4, phar.getQty());
			ps.setInt(5, phar.getId());
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean deletePharmaceutical(int id) {
		boolean f = false;
		try {
			String sql = "delete from pharmaceuticals where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
		
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean subtractPharmaceutical(Pharmaceutical phar) {
		boolean f = false;
		try {
			String sql = "update pharmaceuticals set quantity=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, phar.getQty());
			ps.setInt(2, phar.getId());
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
