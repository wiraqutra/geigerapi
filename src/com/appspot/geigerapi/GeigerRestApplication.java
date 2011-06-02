package com.appspot.geigerapi;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.appspot.geigerapi.rest.GeigerDataResource;

public final class GeigerRestApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
        Router router = new Router(this.getContext());
        router.attachDefault(GeigerDataResource.class);
        return router;
	}

	
}
