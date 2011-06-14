package com.appspot.geigerapi.response;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.appspot.geigerapi.datagroup.ForecastRadiationDatagroup;
import com.appspot.geigerapi.entity.Radiation;

public final class ForecastRadiationResponseBuilder extends
		RadiationResponseBuilderBase<ForecastRadiationDatagroup> {

	@Override
	public ForecastRadiationDatagroup getRadiationModel(Radiation radiation) {
		return new ForecastRadiationDatagroup(radiation);
	}

	@Override
	protected GenericEntity<List<ForecastRadiationDatagroup>> createListGenericEntity(List<ForecastRadiationDatagroup> models) {
		return new GenericEntity<List<ForecastRadiationDatagroup>>(models){};
	}

}
