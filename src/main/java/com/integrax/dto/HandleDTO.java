package com.integrax.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HandleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NonNull
	@EqualsAndHashCode.Include
	private Long accountId;
	
//	@NonNull
//	@EqualsAndHashCode.Include
//	private Long tenantId;
	
	private Boolean active;
	private String language;	
	private String token;	
	
	@JsonProperty("mContext")
	private Map<String, Object> mContext = new HashMap<>();
	
	public void setContextInformation(String key, Object value) {
		this.mContext.put(key, value);
	}
}
