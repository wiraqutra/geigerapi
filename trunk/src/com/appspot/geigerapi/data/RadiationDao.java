package com.appspot.geigerapi.data;

import javax.jdo.PersistenceManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.appspot.geigerapi.entity.Radiation;

public final class RadiationDao extends Dao<Radiation> {
	private static RadiationDao radiationDao;
	
	private RadiationDao() {
		super(Radiation.class);
	}
	
	// It is not needed to be singleton
	// But in almost case, also not need many dao. 
	public static RadiationDao getInstance(){
		if(radiationDao == null){
			radiationDao = new RadiationDao();
		}
		return radiationDao;
	}

	/* (non-Javadoc)
	 * @see com.appspot.geigerapi.data.Dao#save(javax.jdo.PersistenceManager, java.lang.Object)
	 */
	@Override
	protected void save(PersistenceManager pm, Radiation radiation) {
		if(radiation.hasDulicated(pm)){
			throw new WebApplicationException(Response.notModified().build());
		}
		super.save(pm, radiation);
	}

	/* (non-Javadoc)
	 * @see com.appspot.geigerapi.data.Dao#delete(javax.jdo.PersistenceManager, java.lang.Object)
	 */
	@Override
	protected Radiation delete(PersistenceManager pm, Radiation radiation) {
		radiation.checkOwned();
		return super.delete(pm, radiation);
	}
}
