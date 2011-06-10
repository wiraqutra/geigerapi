package com.appspot.geigerapi.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public final class Authorization {
	public static boolean isLoggedIn(){
		return UserServiceFactory.getUserService().isUserLoggedIn();
	}

	public static String getEmail(){
		UserService userService = UserServiceFactory.getUserService();
		return userService.isUserLoggedIn() ? userService.getCurrentUser().getEmail() : null;
	}


	public static String getURL(String dest){
		return UserServiceFactory.getUserService().createLoginURL(dest);
	}
}
