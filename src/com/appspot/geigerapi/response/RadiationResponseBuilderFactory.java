package com.appspot.geigerapi.response;

public final class RadiationResponseBuilderFactory {
	public static IRadiationResponseBuilder get(String datagroup){
		return new MinRadiationResponseBuilder();
	}
}
