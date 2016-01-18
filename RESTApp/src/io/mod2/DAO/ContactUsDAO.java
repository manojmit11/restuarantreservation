package io.mod2.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;

import io.mod2.controller.ContactUsController;
import io.mod2.exceptions.AppException;
import io.mod2.model.ContactUs;
import io.mod2.util.DButil;

public class ContactUsDAO {

	public ContactUs findAll() throws AppException {
		ContactUs contact = null;
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM contactus");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				contact =new ContactUs();
				contact.setHotelName(rs.getString("HOTEL_NAME"));
				contact.setAddress(rs.getString("ADDRESS1"));
				contact.setPhoneNumber(rs.getString("PHONE"));
				contact.setHoursOfOperation(rs.getString("HOURS_OPERATION"));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		} catch(NullPointerException e){
			e.printStackTrace();
			throw new NotFoundException("No Employee found");
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return contact;
	}

	public ContactUs create (ContactUs contact) throws AppException {
		
		
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("INSERT INTO contactus (HOTEL_NAME, ADDRESS1,PHONE,HOURS_OPERATION) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, contact.getHotelName());
			ps.setString(2, contact.getAddress());
			ps.setString(3, contact.getPhoneNumber());
			ps.setString(4, contact.getHoursOfOperation());
					
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return contact;
	}

	public ContactUs update( ContactUs contact, String id) throws AppException {
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String S2= "Update rest.contactus SET "+"HOTEL_NAME='"+contact.getHotelName()
												+"' , ADDRESS1='"+contact.getAddress()
												+"' , PHONE='"+contact.getPhoneNumber()
												+"' , HOURS_OPERATION='"+contact.getHoursOfOperation()
												+"' WHERE HOTEL_NAME='"+id+"'";
			
			System.out.println(S2);
			
			/*
			ps = con.prepareStatement("Update rest.reservations SET FIRST_NAME=?, LAST_NAME=?, TABLE_NUMBER=?, RESERVATION_DATE=?, RESERVATION_TIME=? WHERE CONFIRMATION_NUMBER=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, res.getFirstName());
			ps.setString(2, res.getLastName());
			ps.setString(3, res.getTable());
			ps.setDate(4, res.getdate());
			ps.setTime(5, res.gettime());
			
			*/
			ps = con.prepareStatement(S2,PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				/*res.setconfirmationnumber(rs.getInt(1));*/
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return contact;
	}	
	
	
	
	
public void delete() throws AppException {
		
		ContactUsController Contactcontrol= new ContactUsController();
		ContactUs contact = null;
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		contact= Contactcontrol.getDetails();
		
		try {
			ps = con.prepareStatement("DELETE FROM contactus");
						
			ps.executeUpdate();
			
			if(contact!=null) {
				System.out.println("Sucessfully Deleted");
			}
			else {
				throw new NotFoundException("No Credentials available to delete");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				
				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	
}
