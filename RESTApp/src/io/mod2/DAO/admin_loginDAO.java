package io.mod2.DAO;

import io.mod2.controller.AdminController;
import io.mod2.exceptions.AppException;
import io.mod2.model.Admin_Login;
import io.mod2.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.NotFoundException;




  public class admin_loginDAO {
	  
	
	  public Admin_Login findAll() throws AppException {
		  
			Admin_Login admin = null;
			Connection con = DButil.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement("SELECT * FROM admin");
				rs = ps.executeQuery();
				
				while(rs.next()) {
					admin =new Admin_Login();
					admin.setUser_Name(rs.getString("USER_NAME"));
					admin.setAdmin_Password(rs.getString("ADMIN_PASSWORD"));
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
			
			return admin;
		}

	
	
	
	public Admin_Login create (Admin_Login admin) throws AppException {
		
		
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("INSERT INTO admin (USER_NAME, ADMIN_PASSWORD) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, admin.getUser_Name());
			ps.setString(2, admin.getAdmin_Password());
					
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
		
		return admin;
	}
	
	public Admin_Login update( Admin_Login admin, String id) throws AppException {
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String S2= "Update rest.admin SET "+"USER_NAME='"+admin.getUser_Name()
												+"' , ADMIN_PASSWORD='"+admin.getAdmin_Password()
												+"' WHERE USER_NAME='"+id+"'";
			
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
		
		return admin;
	}	
	
	
public void delete() throws AppException {
		
		AdminController admincontrol= new AdminController();
		Admin_Login admin = null;
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		admin= admincontrol.getCredentials();
		
		try {
			ps = con.prepareStatement("DELETE FROM contactus");
						
			ps.executeUpdate();
			
			if(admin!=null) {
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
