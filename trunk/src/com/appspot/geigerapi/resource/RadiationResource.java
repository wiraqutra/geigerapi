package com.appspot.geigerapi.resource;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.appspot.geigerapi.PMF;
import com.appspot.geigerapi.Radiation;
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

	@POST
	@Path("min.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Radiation addJsonRadiation(JAXBElement<Radiation> jaxbData){
		Radiation radiation = jaxbData.getValue();
		saveRadiation(radiation);
		return radiation;
	}

// XXX This method is not working. The data acquisition from request body was failed.
//
//	@POST
//	@Path("min.csv")
//	@Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
//	@Transactional
//	public String addCsvRadiation(){
//		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
//		Date datetime = new Date();
//		try {
//			datetime = Radiation.DATE_FORMAT.parse(params.getFirst("datetime"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String label = params.getFirst("label");
//		int valuetype = Integer.parseInt(params.getFirst("valuetype"));
//		double radiovalue = Double.parseDouble(params.getFirst("radiovalue"));
//		double lat = Double.parseDouble(params.getFirst("lat"));
//		double lon = Double.parseDouble(params.getFirst("lon"));
//
//		saveRadiation(radiation);
//		StringBuffer buffer = new StringBuffer();
//		appendRadiationToStringBuffer(buffer,radiation);
//		return buffer.toString();
//	}

	private void saveRadiation(Radiation radiation) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(radiation);
		}finally{
			pm.close();
		}
	}

	@PUT
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Radiation updateJsonRadiation(@PathParam("id") Long id, JAXBElement<Radiation> jaxbData){
		Radiation target = jaxbData.getValue();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Radiation origin = pm.getObjectById(Radiation.class, id);
		if(origin == null) throw new NotFoundException("Id = " + id + " not found");
		if(origin.equals(target)){
			Response.notModified().build();
			return origin;
		}else{
			target.setId(id);
			try{
				pm.makePersistent(target);
			}finally{
				pm.close();
			}
			return target;
		}
	}

	@DELETE
	@Path("min/{id}.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Radiation removeRadiation(@PathParam("id") Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Radiation radiation = pm.getObjectById(Radiation.class, id);
		if(radiation == null) throw new NotFoundException("Id = " + id + " not found");
		try{
			pm.deletePersistent(radiation);
		}finally{
			pm.close();
		}
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
