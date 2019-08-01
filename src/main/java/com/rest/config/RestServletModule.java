package com.rest.config;

import com.google.inject.AbstractModule;
import com.rest.controller.ContactController;
import com.rest.service.ContactPhoneImpl;
import com.rest.service.ContactPhoneService;

public class RestServletModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ContactController.class);
		bind(ContactPhoneService.class).to(ContactPhoneImpl.class);
	}

}
