package io.mod2.model;

import java.sql.Time;
import java.sql.Date;

public class Reservation {

	private int confirmationnumber;
	private String firstName;
	private String lastName;
	private String Table;
	private Date date;
	private Time time;
		
	public int getconfirmationnumber() {
		return confirmationnumber;
	}
	public void setconfirmationnumber(int confirmationnumber) {
		this.confirmationnumber = confirmationnumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTable() {
		return Table;
	}
	public void setTable(String Table) {
		this.Table = Table;
	}
	public Date getdate() {
		return date;
	}
	public void setdate(Date date) {
		this.date = date;
	}
	public Time gettime() {
		return time;
	}
	public void settime(Time time) {
		this.time = time;
	}
	
}

