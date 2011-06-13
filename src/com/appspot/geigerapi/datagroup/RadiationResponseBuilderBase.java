package com.appspot.geigerapi.datagroup;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

import com.appspot.geigerapi.entity.Radiation;
import com.appspot.geigerapi.model.OutputDataModel;

public abstract class RadiationResponseBuilderBase<T extends OutputDataModel> implements IRadiationResponseBuilder{
	
	public Response getRadiationsResponse(List<Radiation> radiations,
			String extension) {
		List<T> models = new ArrayList<T>();
		for(Radiation radiation:radiations){
			models.add(getRadiationModel(radiation));
		}
		if(extension.equalsIgnoreCase("csv")){
			StringBuffer buffer = new StringBuffer();
			for(OutputDataModel model:models){
				model.writeCsvTo(buffer);
			}
			return Response.ok(buffer.toString(),MediaType.TEXT_PLAIN).build();
		}else if (extension.equalsIgnoreCase("json")){
			return Response.ok(createListGenericEntity(models),MediaType.APPLICATION_JSON).build();
		}else{
			List<Variant> variants =
				Variant.mediaTypes(MediaType.APPLICATION_JSON_TYPE,MediaType.TEXT_PLAIN_TYPE).build();
			return Response.notAcceptable(variants).build();
		}
	}

	public Response getRadiationResponse(Radiation radiation,
			String extension) {
		T model = getRadiationModel(radiation);
		if(extension.equalsIgnoreCase("csv")){
			StringBuffer buffer = new StringBuffer();
			model.writeCsvTo(buffer);
			return Response.ok(buffer.toString(),MediaType.TEXT_PLAIN).build();
		}else if (extension.equalsIgnoreCase("json")){
			return Response.ok(model,MediaType.APPLICATION_JSON).build();
		}else{
			List<Variant> variants =
				Variant.mediaTypes(MediaType.APPLICATION_JSON_TYPE,MediaType.TEXT_PLAIN_TYPE).build();
			return Response.notAcceptable(variants).build();
		}
	}
	public Response getRadiationCreatedResponse(Radiation radiation,
			String extension,URI uri) {
		T model = getRadiationModel(radiation);
		if (extension.equalsIgnoreCase("json")){
			return Response.created(uri).entity(model).type(MediaType.APPLICATION_JSON).build();
		}else{
			List<Variant> variants =
				Variant.mediaTypes(MediaType.APPLICATION_JSON_TYPE).build();
			return Response.notAcceptable(variants).build();
		}
	}
	
	public abstract T getRadiationModel(Radiation radiation);

	protected abstract GenericEntity<List<T>> createListGenericEntity(List<T> models);

}
