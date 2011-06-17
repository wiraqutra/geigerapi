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
import com.appspot.geigerapi.input.RadiationInputModel;
import com.appspot.geigerapi.response.IRadiationResponseBuilder;
import com.appspot.geigerapi.response.RadiationResponseBuilderFactory;

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
	@Path("{datagroup}.{ext}")
	public Response listAll(
			@PathParam("datagroup") String datagroup,
			@PathParam("ext") String extension){
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String order = params.containsKey("order") ? params.getFirst("order") : "datetime";
		IRadiationResponseBuilder responseBuilder = RadiationResponseBuilderFactory.get(datagroup);
		List<Radiation> radiations = radiationDao.getAllowedAll(order);
		return responseBuilder.getRadiationsResponse(radiations, extension);
	}

	@GET
	@Path("{datagroup}/{id}.{ext}")
	public Response listOne(@PathParam("id") Long id,
			@PathParam("datagroup") String datagroup,
			@PathParam("ext") String extension){
		Radiation radiation = this.radiationDao.get(id);
		if(radiation.getHidden()){
			if(!Authorization.isLoggedIn() || !radiation.isOwned()){
				throw new WebApplicationException(403);
			}
		}
		IRadiationResponseBuilder responseBuilder = RadiationResponseBuilderFactory.get(datagroup);
		return responseBuilder.getRadiationResponse(radiation, extension);
	}

	
	@POST
	@Path("{datagroup}.{ext}")
	@Transactional
	public Response addRadiation(JAXBElement<RadiationInputModel> jaxbData,
			@PathParam("datagroup") String datagroup,
			@PathParam("ext") String extension){
		if(!Authorization.isLoggedIn()) throwUnauthorized();
		Radiation radiation = jaxbData.getValue().create();
		this.radiationDao.save(radiation);
		//TODO Remove hard coded resource path.
		URI uri = uriInfo.getBaseUriBuilder().path("radiation/"+datagroup+"/"+radiation.getId().toString()+"."+extension).build();
		IRadiationResponseBuilder responseBuilder = RadiationResponseBuilderFactory.get(datagroup);
		return responseBuilder.getRadiationCreatedResponse(radiation,extension,uri);
	}

	@PUT
	@Path("{datagroup}/{id}.{ext}")
	@Transactional
	public Response updateRadiation(@PathParam("id") Long id, JAXBElement<RadiationInputModel> jaxbData,
		@PathParam("datagroup") String datagroup,
		@PathParam("ext") String extension){
		if(!Authorization.isLoggedIn()) throwUnauthorized();
		Radiation target = jaxbData.getValue().create();
		Radiation origin = this.radiationDao.get(id);
		if(!origin.isOwned()){
			throw new WebApplicationException(403);
		}
		target.setId(id);
		this.radiationDao.save(target);
		URI uri = uriInfo.getAbsolutePath();
		IRadiationResponseBuilder responseBuilder = RadiationResponseBuilderFactory.get(datagroup);
		return responseBuilder.getRadiationCreatedResponse(target,extension,uri);
	}

	@DELETE
	@Path("{datagroup}/{id}.{ext}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response removeRadiation(@PathParam("id") Long id,
		@PathParam("datagroup") String datagroup,
		@PathParam("ext") String extension){
		if(!Authorization.isLoggedIn()) throwUnauthorized();
		Radiation radiation = this.radiationDao.delete(id);
		IRadiationResponseBuilder responseBuilder = RadiationResponseBuilderFactory.get(datagroup);
		return responseBuilder.getRadiationResponse(radiation, extension);
	}

	private void throwUnauthorized() {
		String url = Authorization.getURL(uriInfo.getAbsolutePath().toString());
		throw new WebApplicationException(Response.status(401).entity(url).build());
	}

}
