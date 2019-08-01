package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.rest.model.Contact;
import com.rest.model.Phones;

public class ContactPhoneImpl implements ContactPhoneService{

	@Inject
	Provider<EntityManager> emProvider;
	
	EntityManager em;

	@Override
	@Transactional
	public void addData(String name, List<Phones> phones) {
		em = emProvider.get();
		Contact c = new Contact();
		c.setCname(name);
		c.setPhones(phones);
		em.persist(c);
		for(Phones p : phones) {
			p.setContact(c);
			em.persist(p);
		}	
	}

	@Override
	@Transactional
	public void updateData(int id, String name, List<Phones> phones) {
		em = emProvider.get();
		Contact c = em.find(Contact.class, (long)id);
		c.setCname(name);
		int counter=0;
		List<Phones> phnList = c.getPhones();
		
		for(Phones phn : phones){
			Phones p = phnList.get(counter);
			if(!phn.getPhoneNumber().equals(phnList.get(counter).getPhoneNumber())){
				p.setPhoneNumber(phn.getPhoneNumber());
			}
			if(!phn.getType().equals(phnList.get(counter).getType())){
				p.setType(phn.getType());
			}
			counter++;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllData() {
		em = emProvider.get();
		List<Contact> contacts = em.createQuery("select c from Contact c").getResultList();
		return contacts;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteData(int id) {
		em = emProvider.get();
		Contact c = em.find(Contact.class, (long)id);
		c.setPhones(null);
		ArrayList<Phones> phn = (ArrayList<Phones>)em.createQuery("select p from Phones p where Contact_cid = "+c.getCid()).getResultList();
		for(Phones p : phn) {
			em.remove(p);
		}
		em.remove(c);
	}

	@Override
	public Contact getContact(int id) {
		em = emProvider.get();
		return em.find(Contact.class, (long)id);
	}
	
}