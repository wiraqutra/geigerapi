package com.appspot.geigerapi.response;

import com.sun.jersey.api.NotFoundException;

public final class RadiationResponseBuilderFactory {
	public static IRadiationResponseBuilder get(String datagroup){
		if(datagroup.equals("min")) return new MinRadiationResponseBuilder();
		if(datagroup.equals("forecast")) return new ForecastRadiationResponseBuilder();
		if(datagroup.equals("detail")) return new DetailRadiationResponseBuilder();
		if(datagroup.equals("notes")) return new NotesRadiationResponseBuilder();
		if(datagroup.equals("full")) return new FullRadiationResponseBuilder();
		throw new NotFoundException("datagroup: " + datagroup + " not defined!");
	}
}
