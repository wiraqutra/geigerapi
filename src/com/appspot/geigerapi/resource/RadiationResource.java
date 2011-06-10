package com.appspot.geigerapi.resource;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;
import javax.ws.rs.Consumes;
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

import com.appspot.geigerapi.PMF;
import com.appspot.geigerapi.Radiation;
import com.appspot.geigerapi.auth.Authorization;
import com.sun.jersey.api.NotFoundException;

@Path("/radiation")
public final class RadiationResource {
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
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
			appendRadiationToStringBuffer(buffer, radiation);
		}
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	private List<Radiation> getAll() {
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String order = params.containsKey("order") ? params.getFirst("order") : "datetime";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Radiation.class);
		query.setOrdering(order);
		return (List<Radiation>)query.execute();
	}
	
	@GET
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Radiation listOneByJson(@PathParam("id") Long id){
		return getOne(PMF.get().getPersistenceManager(),id);
	}

	@GET
	@Path("min/{id}.csv")
	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
	public String listOneByCsv(@PathParam("id") Long id) {
		StringBuffer buffer = new StringBuffer();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		appendRadiationToStringBuffer(buffer, getOne(pm,id));
		return buffer.toString();
	}


	@POST
	@Path("min.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response addJsonRadiation(JAXBElement<Radiation> jaxbData){
		checkLoggedIn();
		Radiation radiation = jaxbData.getValue();
		saveRadiation(radiation);
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
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Radiation origin = getOne(pm, id);
		checkOwnedRadiation(origin);
		target.setId(id);
		saveRadiation(pm, target);
		URI uri = uriInfo.getAbsolutePath();
		return Response.created(uri).entity(target).build();
	}

	@DELETE
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Radiation removeRadiation(@PathParam("id") Long id){
		checkLoggedIn();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Radiation radiation = getOne(pm, id);
		checkOwnedRadiation(radiation);
		try{
			pm.deletePersistent(radiation);
		}finally{
			pm.close();
		}
		return radiation;
	}

	private void saveRadiation(Radiation radiation) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		saveRadiation(pm, radiation);
	}

	private void saveRadiation(PersistenceManager pm, Radiation radiation) {
		System.out.println("saving!");
		if(radiation.hasDulicated(pm)){
			throw new WebApplicationException(Response.notModified().entity(radiation).build());
		}
		try{
			pm.makePersistent(radiation);
		}finally{
			pm.close();
		}
	}

	private void checkLoggedIn() {
		if(!Authorization.isLoggedIn()){
			String url = Authorization.getURL(uriInfo.getAbsolutePath().toString());
			throw new WebApplicationException(Response.status(401).entity(url).build());
		}
	}

	private void checkOwnedRadiation(Radiation radiation) {
		if(!radiation.getEmail().equals(Authorization.getEmail())){
			throw new WebApplicationException(403);
		}
	}

	private Radiation getOne(PersistenceManager pm, Long id) {
		Radiation radiation = pm.getObjectById(Radiation.class, id);
		if(radiation == null) throw new NotFoundException("Id = " + id + " not found");
		return radiation;
	}

	private void appendRadiationToStringBuffer(StringBuffer buffer,
			Radiation radiation) {
		buffer.append(Radiation.DATE_FORMAT.format(radiation.getDatetime()));
		buffer.append(",");
		buffer.append(radiation.getLabel());
		buffer.append(",");
		buffer.append((radiation.getValuetype()));
		buffer.append(",");
		buffer.append(radiation.getLat());
		buffer.append(",");
		buffer.append(radiation.getLon());
		buffer.append(",");
		buffer.append(radiation.getRadiovalue());
		buffer.append("\r\n");
	}
	
}
