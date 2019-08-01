package com.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.plugins.providers.html.View;

import com.google.inject.Inject;
import com.rest.model.Contact;
import com.rest.model.Phones;
import com.rest.service.ContactPhoneService;;

@Path("/")
public class ContactController {

	@Inject
	ContactPhoneService contactPhoneService;

	@GET
	@Path("/get")
	public View showData(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		List<Contact> details = (ArrayList<Contact>) contactPhoneService.getAllData();
		//request.setAttribute("list", details);
		//request.getRequestDispatcher("/insert.jsp").forward(request, response);
		return new View("/insert.jsp",details,"list");
	}
	
	@Path("/add")
	@POST
	public void insert(@FormParam("cname") String name, @FormParam("contactNo") String[] contactNos,
			@FormParam("contactType") String[] types, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ServletException, IOException {

		// also get value by request response object...
		// String nm = request.getParameter("cname");
		// System.out.println(nm);

		List<Phones> phones = new ArrayList<Phones>();
		for (int i = 0; i < types.length; i++) {
			Phones p = new Phones();
			p.setType(types[i]);
			p.setPhoneNumber(contactNos[i]);
			phones.add(p);
		}
		contactPhoneService.addData(name, phones);
		response.sendRedirect(request.getContextPath());
	}

	@Path("/delete")
	@GET
	public void deleteEmployee(@QueryParam("id") int id, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ServletException, IOException {
		contactPhoneService.deleteData(id);
		response.sendRedirect(request.getContextPath());
	}

	@Path("/edit")
	@GET
	public void editEmployee(@QueryParam("id") int id, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws ServletException, IOException {
		Contact detail = contactPhoneService.getContact(id);
		request.setAttribute("contactDetail", detail);
		request.getRequestDispatcher("/insert.jsp").forward(request, response);
	}

	@Path("/update")
	@POST
	public void updateEmployee(@QueryParam("id") int id, @FormParam("cname") String name,
			@FormParam("contactNo") String[] contactNos, @FormParam("contactType") String[] types,
			@Context HttpServletRequest request, @Context HttpServletResponse response)
			throws ServletException, IOException {

		List<Phones> phones = new ArrayList<Phones>();
		for (int i = 0; i < types.length; i++) {
			Phones p = new Phones();
			p.setType(types[i]);
			p.setPhoneNumber(contactNos[i]);
			phones.add(p);
		}
		contactPhoneService.updateData(id, name, phones);
		response.sendRedirect(request.getContextPath());
	}
}