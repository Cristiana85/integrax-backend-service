package com.integrax.service;

import java.util.List;

import org.springframework.data.jpa.repository.Lock;

import com.integrax.dto.HandleDTO;
import com.integrax.dto.ProjectDTO;
import com.integrax.dto.ResultDTO;

import jakarta.persistence.LockModeType;

public interface ProjectService {
	
	@Lock(LockModeType.OPTIMISTIC)
	public List<ProjectDTO> getlProject(HandleDTO handleDTO);

	ProjectDTO getProjectById(Long id);

	ResultDTO<Void> deletelProject(HandleDTO handle, List<ProjectDTO> lProject);

	ResultDTO<ProjectDTO> addProject(HandleDTO handle, ProjectDTO project);

	public ResultDTO<ProjectDTO> editProject(HandleDTO handle, ProjectDTO project);

	ProjectDTO saveProject(HandleDTO handle, ProjectDTO project);

	void savelProject(HandleDTO handle, List<ProjectDTO> lProject);

}

