package com.integrax.controller;

public class EndPoint {

	public static class Login {
		public static final String ROOT = "login";
		
		public static final String RECOVER = "recover";
	}
	
	public static class Logout {
		public static final String ROOT = "fw/logout";
	}
	
	public static class Project {
		public static final String ROOT = "projects";
		public static final String ADD = "/add";
		public static final String EDIT = "/edit";
	}
	
	public static class Template {
		public static final String ROOT = "templates";
	}
}
