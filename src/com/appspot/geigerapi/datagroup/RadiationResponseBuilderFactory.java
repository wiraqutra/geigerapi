package com.appspot.geigerapi.datagroup;

public final class RadiationResponseBuilderFactory {
	public static IRadiationResponseBuilder get(String datagroup){
		return new MinRadiationResponseBuilder();
	}
}
