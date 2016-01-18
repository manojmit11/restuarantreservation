package io.mod2.controller;

import io.mod2.DAO.ContactUsDAO;
import io.mod2.exceptions.AppException;
import io.mod2.model.ContactUs;

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
import javax.ws.rs.core.Response.Status;


@Path("/contactus")
public class ContactUsController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContactUs getDetails(){
		
			ContactUs contact= null;

		try{
			ContactUsDAO dao= new ContactUsDAO();
			contact = dao.findAll();
			
		}catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);

		}
		
		return contact;
				
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ContactUs create(ContactUs contact){
		
		try{
			ContactUsDAO dao= new ContactUsDAO();
			contact = dao.create(contact);
			
		}catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);

		}
		
		return contact;
	}
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public ContactUs update(ContactUs contact,@PathParam("id") String id){
		
				
		try {
			ContactUsDAO dao = new ContactUsDAO();
			contact = dao.update(contact, id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return contact;
	}
	
	
	@DELETE
	
    public void delete(){
    	
    	try{
			ContactUsDAO dao= new ContactUsDAO();
			dao.delete();
			
		}catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);

		}
		
    }
}
