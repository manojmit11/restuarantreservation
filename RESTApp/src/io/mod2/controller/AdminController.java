package io.mod2.controller;

import io.mod2.DAO.admin_loginDAO;
import io.mod2.exceptions.AppException;
import io.mod2.model.Admin_Login;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/admin")
public class AdminController{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Admin_Login getCredentials(){
		
		Admin_Login admin= null;
		
		try{
			admin_loginDAO dao= new admin_loginDAO();
			admin = dao.findAll();
			
		}catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);

		}
		
		return admin;
				
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Admin_Login create(Admin_Login admin){
		
				
		try {
			admin_loginDAO dao = new admin_loginDAO();
			admin = dao.create(admin);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return admin;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Admin_Login update(Admin_Login admin,@PathParam("id") String id){
		
				
		try {
			admin_loginDAO dao = new admin_loginDAO();
			admin = dao.update(admin, id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return admin;
	}
	
	@DELETE
		
	public Response delete(){
		
		try {
			admin_loginDAO dao = new admin_loginDAO();
			dao.delete();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
		
	}
	
}