package com.integrax.util;

public class Constants {

	public enum FieldName {
		
		ACCOUNT_ID("accountId"),
		BASIC("Basic "),
		BEARER("Bearer "),
		TENANT_ID("tenantId"),
		TOKEN("token");
		
		private String value;
		FieldName(){}		
		FieldName(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}	
	}
	
	public enum Constant {
		WEB_SOCKET_CHANNEL("/topic/notification"),
		SPLIT_MAIL(";"),
		DECRIPTED_PASSWORD_CONTEXT_KEY("decripted.password"),
		BCRYPT("{bcrypt}"),
		MESSAGES("messages"),
		COLON(":"),
		SEPARATOR("§"),
		PATH("PATH"),
		DOT_REGEX("\\.");
		
		private String value;
		Constant(){}		
		Constant(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
