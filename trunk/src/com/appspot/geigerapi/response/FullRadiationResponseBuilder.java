package com.appspot.geigerapi.response;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.appspot.geigerapi.datagroup.FullRadiationDatagroup;
import com.appspot.geigerapi.entity.Radiation;

public final class FullRadiationResponseBuilder extends
		RadiationResponseBuilderBase<FullRadiationDatagroup> {

	@Override
	public FullRadiationDatagroup getRadiationModel(Radiation radiation) {
		return new FullRadiationDatagroup(radiation);
	}

	@Override
	protected GenericEntity<List<FullRadiationDatagroup>> createListGenericEntity(List<FullRadiationDatagroup> models) {
		return new GenericEntity<List<FullRadiationDatagroup>>(models){};
	}

}
