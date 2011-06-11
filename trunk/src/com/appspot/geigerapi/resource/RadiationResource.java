package com.appspot.geigerapi.resource;

import java.net.URI;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.appspot.geigerapi.auth.Authorization;
import com.appspot.geigerapi.data.RadiationDao;
import com.appspot.geigerapi.entity.Radiation;

@Path("/radiation")
public final class RadiationResource {
	@Context
	private UriInfo uriInfo;
	
	@Context
	private Request request;
	
	private RadiationDao radiationDao = RadiationDao.getInstance();
	
	public RadiationResource() {
	}
	
	@GET
	@Path("min.json")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<Radiation> listAllByJson(){
		return getAll();
	}

	@GET
	@Path("min.csv")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public String listAllByCsv() {
		StringBuffer buffer = new StringBuffer();
		for(Radiation radiation:getAll()){
			radiation.writeCsvTo(buffer);
		}
		return buffer.toString();
	}

	private List<Radiation> getAll() {
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String order = params.containsKey("order") ? params.getFirst("order") : "datetime";
		return this.radiationDao.getAll(order);
	}
	
	@GET
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Radiation listOneByJson(@PathParam("id") Long id){
		return this.radiationDao.get(id);
	}

	@GET
	@Path("min/{id}.csv")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public String listOneByCsv(@PathParam("id") Long id) {
		StringBuffer buffer = new StringBuffer();
		this.radiationDao.get(id).writeCsvTo(buffer);
		return buffer.toString();
	}


	@POST
	@Path("min.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response addJsonRadiation(JAXBElement<Radiation> jaxbData){
		checkLoggedIn();
		Radiation radiation = jaxbData.getValue();
		this.radiationDao.save(radiation);
		//TODO Remove hard coded resource path.
		URI uri = uriInfo.getBaseUriBuilder().path("radiation/min/"+radiation.getId().toString()+".json").build();
		return Response.created(uri).entity(radiation).build();
	}

	@PUT
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response updateJsonRadiation(@PathParam("id") Long id, JAXBElement<Radiation> jaxbData){
		checkLoggedIn();
		Radiation target = jaxbData.getValue();
		Radiation origin = this.radiationDao.get(id);
		origin.checkOwned();
		target.setId(id);
		this.radiationDao.save(target);
		URI uri = uriInfo.getAbsolutePath();
		return Response.created(uri).entity(target).build();
	}

	@DELETE
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Radiation removeRadiation(@PathParam("id") Long id){
		checkLoggedIn();
		return this.radiationDao.delete(id);
	}

	private void checkLoggedIn() {
		if(!Authorization.isLoggedIn()){
			String url = Authorization.getURL(uriInfo.getAbsolutePath().toString());
			throw new WebApplicationException(Response.status(401).entity(url).build());
		}
	}

}
