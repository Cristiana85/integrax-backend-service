package com.integrax.service.impl;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrax.dto.HandleDTO;
import com.integrax.dto.TemplateDTO;
import com.integrax.repository.TemplateRepository;
import com.integrax.service.TemplateService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class TemplateServiceImpl implements TemplateService {

	@NonNull
	private final TemplateRepository templateRepo;

	//    @Autowired
	//    private ApplicationContext context;

	@Autowired
	private final ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TemplateDTO> getlTemplate(HandleDTO handle) {
		return /*templateRepo.findByTenantId(handle.getTenantId())
				.stream()
				.map(c -> modelMapper.map(c, TemplateDTO.class))
				.collect(Collectors.toList());*/
				null;
	}

}
