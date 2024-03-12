package com.integrax.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class AccountDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull(message = "{NotNullUniqueIdValidation}")
	private String uniqueId;
	
	@NotNull(message = "{NotNullTenantIdValidation}")
	private Long tenantId;
	
    private String password;
//    private Boolean active;
//    private String name;
//    private String surname;
//    private Instant loginDateTime; 
//    private Instant accountExpired;
//    private Instant passwordInsertDateTime;
//    private Integer failedAttempts;
//    private Boolean mustChangePassword;	
//    private Integer lockId;
//    
//    private String roles;
//    
//    @NotNull(message = "{NotNullOrganizationIdValidation}")
//    private Long defaultRoleId;
//    
//    private Long organizationId;
//    private String organizationDescription;
//    
//    private Map<String, Object> mContext = new HashMap<>();
//    
//    @JsonProperty("lAccountSetting")
//    private List<AccountSettingDTO> lAccountSetting;
//    
//    @JsonProperty("lAccountRole")
//    private List<AccountRoleDTO> lAccountRole;
    
//    private OrganizationDTO organization;
//    private TenantDTO tenant;
    
//    public void setContext(String key, Object value) {
//		this.mContext.put(key, value);
//	}
//	
//	public Object getContext(String key) {
//		return mContext.get(key);
//	}
//	
//	public void setRoles() {
//		roles = "";
//		
//		if (lAccountRole != null) {
//			roles = lAccountRole.stream()
//			.map(item -> item.getRole().getDescription())
//			.reduce((partialString, item) -> partialString + ", " + item).orElse("");
//		}		
//	}
}
