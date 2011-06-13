package com.appspot.geigerapi.datagroup;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.appspot.geigerapi.entity.Radiation;
import com.appspot.geigerapi.model.RadiationMinModel;

public final class MinRadiationResponseBuilder extends
		RadiationResponseBuilderBase<RadiationMinModel> {

	@Override
	public RadiationMinModel getRadiationModel(Radiation radiation) {
		return new RadiationMinModel(radiation);
	}

	@Override
	protected GenericEntity<List<RadiationMinModel>> createListGenericEntity(List<RadiationMinModel> models) {
		return new GenericEntity<List<RadiationMinModel>>(models){};
	}

}
