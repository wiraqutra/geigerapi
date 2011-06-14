package com.appspot.geigerapi.response;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.appspot.geigerapi.datagroup.MinRadiationDatagroup;
import com.appspot.geigerapi.entity.Radiation;

public final class MinRadiationResponseBuilder extends
		RadiationResponseBuilderBase<MinRadiationDatagroup> {

	@Override
	public MinRadiationDatagroup getRadiationModel(Radiation radiation) {
		return new MinRadiationDatagroup(radiation);
	}

	@Override
	protected GenericEntity<List<MinRadiationDatagroup>> createListGenericEntity(List<MinRadiationDatagroup> models) {
		return new GenericEntity<List<MinRadiationDatagroup>>(models){};
	}

}
