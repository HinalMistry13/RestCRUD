package com.rest.service;

import java.util.List;

import com.rest.model.Contact;
import com.rest.model.Phones;

public interface ContactPhoneService {
	
	public void addData(String name, List<Phones> phones);
	public void updateData(int id, String name, List<Phones> phones);
	public List<Contact> getAllData();
	public void deleteData(int id);
	public Contact getContact(int id); 
}
