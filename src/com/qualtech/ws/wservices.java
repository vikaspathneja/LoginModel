package com.qualtech.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path(value = "getCountries")
public class wservices {
	
	@GET
	@Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
	public String getCountries() {
		return "thanx for calling webservices";
	}
}
