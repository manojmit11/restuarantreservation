package io.mod2.controller;

import io.mod2.DAO.reservationDAO;
import io.mod2.exceptions.AppException;
import io.mod2.model.Reservation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/reservations")
public class ReservationsController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> findAll(){
		
		List<Reservation> reservations =null;
		try {
			reservationDAO dao= new reservationDAO();
			reservations = dao.findAll();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);

		}
		
		return reservations;
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation findOne(@PathParam ("id") int id){
		
		Reservation reservation=null;
		
		try {
			reservationDAO dao= new reservationDAO();
			reservation = dao.findOne(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);

		}
		
		return reservation;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation create(Reservation reservation){
		
				
		try {
			reservationDAO dao = new reservationDAO();
			reservation = dao.create(reservation);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservation;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation update(Reservation reservation,@PathParam("id") int id){
		
		
		try {
			reservationDAO dao = new reservationDAO();
			reservation = dao.update( reservation,id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservation;
		
	}
	
	@DELETE
	@Path("/{id}")
	
	public Response delete(@PathParam("id") int id){
		
		
		
		try {
			reservationDAO dao = new reservationDAO();
			dao.delete(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
		
	}
}

