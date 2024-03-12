package com.integrax.service;

import java.util.List;

import com.integrax.dto.HandleDTO;
import com.integrax.dto.TemplateDTO;

public interface TemplateService {
	
	public List<TemplateDTO> getlTemplate(HandleDTO handle);

}

