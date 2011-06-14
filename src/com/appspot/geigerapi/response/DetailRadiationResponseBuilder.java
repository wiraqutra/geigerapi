package com.appspot.geigerapi.response;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.appspot.geigerapi.datagroup.DetailRadiationDatagroup;
import com.appspot.geigerapi.entity.Radiation;

public final class DetailRadiationResponseBuilder extends
		RadiationResponseBuilderBase<DetailRadiationDatagroup> {

	@Override
	public DetailRadiationDatagroup getRadiationModel(Radiation radiation) {
		return new DetailRadiationDatagroup(radiation);
	}

	@Override
	protected GenericEntity<List<DetailRadiationDatagroup>> createListGenericEntity(List<DetailRadiationDatagroup> models) {
		return new GenericEntity<List<DetailRadiationDatagroup>>(models){};
	}

}
