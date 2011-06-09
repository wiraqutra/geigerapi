package com.appspot.geigerapi.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.appspot.geigerapi.GeigerData;
import com.appspot.geigerapi.PMF;

public final class GeigerDataResource extends ServerResource {
	private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

	public GeigerDataResource() {
		Set<Method> allowedMethods = new HashSet<Method>();
		//allowedMethods.add(Method.GET);
		allowedMethods.add(Method.POST);
		setAllowedMethods(allowedMethods);
	}

	@Post
	@Transactional
	public JsonRepresentation addGeigerData(Representation entity) throws JSONException, IOException{
		JsonRepresentation jsonRep = new JsonRepresentation(entity.getText());
		if (entity.getMediaType().equals(MediaType.APPLICATION_WWW_FORM,true)) {
			Form form = new Form(entity);
			Date datetime = new Date();
			String datatimeString = form.getFirstValue("datetime");
			if(datatimeString != null){
				try {
					datetime = df.parse(datatimeString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String label = form.getFirstValue("label");
			int valuetype = Integer.parseInt(form.getFirstValue("valuetype","0"));
			double radiovalue = Double.parseDouble(form.getFirstValue("radiovalue","0.0"));
			double lat = Double.parseDouble(form.getFirstValue("lat","0.0"));
			double lon = Double.parseDouble(form.getFirstValue("lon","0.0"));

			GeigerData geigerData = new GeigerData(datetime,label,valuetype,radiovalue,lat,lon);
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try{
				pm.makePersistent(geigerData);
			}finally{
				pm.close();
			}
		}
		JSONArray jsonArray = jsonRep.getJsonArray();
		return new JsonRepresentation(jsonArray);
	}

	
}
