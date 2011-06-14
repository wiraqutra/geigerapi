package com.appspot.geigerapi.response;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import com.appspot.geigerapi.datagroup.NotesRadiationDatagroup;
import com.appspot.geigerapi.entity.Radiation;

public final class NotesRadiationResponseBuilder extends
		RadiationResponseBuilderBase<NotesRadiationDatagroup> {

	@Override
	public NotesRadiationDatagroup getRadiationModel(Radiation radiation) {
		return new NotesRadiationDatagroup(radiation);
	}

	@Override
	protected GenericEntity<List<NotesRadiationDatagroup>> createListGenericEntity(List<NotesRadiationDatagroup> models) {
		return new GenericEntity<List<NotesRadiationDatagroup>>(models){};
	}

}
