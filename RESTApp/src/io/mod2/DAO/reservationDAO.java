package io.mod2.DAO;

import io.mod2.controller.ReservationsController;
import io.mod2.exceptions.AppException;
import io.mod2.model.Reservation;
import io.mod2.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class reservationDAO {

	public List<Reservation> findAll() throws AppException {
		List<Reservation> Reservations = new ArrayList<Reservation>();
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM  reservations");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation res = new Reservation();
				res.setconfirmationnumber(rs.getInt("CONFIRMATION_NUMBER"));
				res.setFirstName(rs.getString("FIRST_NAME"));
				res.setLastName(rs.getString("LAST_NAME"));
				res.setTable(rs.getString("TABLE_NUMBER"));
				res.setdate(rs.getDate("RESERVATION_DATE"));
				res.settime(rs.getTime("RESERVATION_TIME"));
								
				Reservations.add(res);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		} /*catch(NullPointerException e){
			e.printStackTrace();
			throw new NotFoundException("Employee with this id not found");
		}*/
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
		
		return Reservations;
	}

	public Reservation findOne(int id) throws AppException, NotFoundException {
		Reservation res = null;
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservations WHERE CONFIRMATION_NUMBER=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				res = new Reservation();
				res.setconfirmationnumber(rs.getInt("CONFIRMATION_NUMBER"));
				res.setFirstName(rs.getString("FIRST_NAME"));
				res.setLastName(rs.getString("LAST_NAME"));
				res.setTable(rs.getString("TABLE_NUMBER"));
				res.setdate(rs.getDate("RESERVATION_DATE"));
				res.settime(rs.getTime("RESERVATION_TIME"));
			}
			else {
				throw new NotFoundException("Reservation with this id not found");
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
		
		return res;
	}

	public Reservation create (Reservation res) throws AppException {
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("INSERT INTO reservations (CONFIRMATION_NUMBER,FIRST_NAME, LAST_NAME,TABLE_NUMBER, RESERVATION_DATE, RESERVATION_TIME) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, res.getconfirmationnumber());
			ps.setString(2, res.getFirstName());
			ps.setString(3, res.getLastName());
			ps.setString(4, res.getTable());
			ps.setDate(5, res.getdate());
			ps.setTime(6, res.gettime());
						
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				/*res.setconfirmationnumber(rs.getInt(1))*/;
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
		
		return res;
	}

	public Reservation update( Reservation res ,int id) throws AppException {
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String S2= "Update rest.reservations SET "+"CONFIRMATION_NUMBER='"+res.getconfirmationnumber()
												+"' , FIRST_NAME='"+res.getFirstName()
												+"',LAST_NAME='"+res.getLastName()
												+"',TABLE_NUMBER='"+res.getTable()
												+"',RESERVATION_DATE='"+res.getdate()
												+"',RESERVATION_TIME='"+res.gettime()
												+"' WHERE CONFIRMATION_NUMBER='"+id+"'";
			
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
		
		return res;
	}

	public Reservation delete(int id) throws AppException {
		
		ReservationsController rescontroller= new ReservationsController();
		Reservation res = null;
		Connection con = DButil.connect();
		PreparedStatement ps = null;
		res= rescontroller.findOne(id);
		
		try {
			ps = con.prepareStatement("DELETE FROM reservations WHERE CONFIRMATION_NUMBER=?");
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			if(res!=null) {
				System.out.println("Sucessfully Deleted");
			}
			else {
				throw new NotFoundException("Reservation with this id not found");
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
		
		return res;
		
	}

	
}
