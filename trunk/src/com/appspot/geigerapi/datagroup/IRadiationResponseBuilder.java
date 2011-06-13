package com.appspot.geigerapi.datagroup;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response;

import com.appspot.geigerapi.entity.Radiation;
import com.appspot.geigerapi.model.OutputDataModel;

public interface IRadiationResponseBuilder {
	
	public Response getRadiationsResponse(List<Radiation> radiations,
			String extension);
	
	public Response getRadiationResponse(Radiation radiation,
			String extension);
	
	public Response getRadiationCreatedResponse(Radiation radiation,
			String extension,URI uri);
}
