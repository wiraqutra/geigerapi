package com.appspot.geigerapi.resource;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.appspot.geigerapi.PMF;
import com.appspot.geigerapi.Radiation;



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
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	private List<Radiation> getAll() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Radiation.class);
		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
		String order = params.containsKey("order") ? params.getFirst("order") : "datetime";
		query.setOrdering(order);
		return (List<Radiation>)query.execute();
	}

	@POST
	@Path("min.json")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Radiation addGeigerData(JAXBElement<Radiation> jaxbData){
		Radiation radiation = jaxbData.getValue();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(radiation);
		}finally{
			pm.close();
		}
		return radiation;
	}

	
}
