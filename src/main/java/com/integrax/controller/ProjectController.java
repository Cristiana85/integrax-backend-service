package com.integrax.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.integrax.dto.ProjectDTO;
import com.integrax.dto.ResultDTO;
import com.integrax.service.ProjectService;
import com.integrax.util.IntegraUtils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "https://integrax-backend-gcl-3hkbcb6pua-uc.a.run.app", maxAge = 3600)
@RequiredArgsConstructor
public class ProjectController {

	@NonNull
	private final ProjectService projectService;

	@GetMapping(value = "projects")
	public ResponseEntity<ResultDTO<List<ProjectDTO>>> getlProject(
			@RequestHeader("Authorization") String authorization, 
			@RequestHeader("AccountId") String accountId) {
		return new ResponseEntity<>(new ResultDTO<>(projectService.getlProject(IntegraUtils.getHandleFromHeader(authorization, accountId))), HttpStatus.OK);
	}
	
	@PutMapping(value = "projects/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> saveProject(
			@RequestHeader("Authorization") String authorization, 
			@RequestHeader("AccountId") String accountId,	
			@RequestBody ProjectDTO project, BindingResult bindingResult) {
		return new ResponseEntity<>(projectService.saveProject(IntegraUtils.getHandleFromHeader(authorization, accountId), project), HttpStatus.OK);		
	}
	
	@PutMapping(value = EndPoint.Project.EDIT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultDTO<ProjectDTO>> editProject(
			@RequestHeader("Authorization") String authorization, 
			@RequestHeader("AccountId") String accountId,	
			@RequestBody ProjectDTO project, BindingResult bindingResult) {
		return new ResponseEntity<>(projectService.editProject(IntegraUtils.getHandleFromHeader(authorization, accountId), project), HttpStatus.OK);		
	}
	
	@PostMapping(value = "projects/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultDTO<ProjectDTO>> addProject(
			@RequestHeader("Authorization") String authorization, 
			@RequestHeader("AccountId") String accountId,	
			@RequestBody ProjectDTO project, BindingResult bindingResult) {
		return new ResponseEntity<>(projectService.addProject(IntegraUtils.getHandleFromHeader(authorization, accountId), project), HttpStatus.OK);		
	}
	
	@DeleteMapping(value = "/projects")
	public ResponseEntity<ResultDTO<Void>> deletelProject(
			@RequestHeader("Authorization") String authorization, 
			@RequestHeader("AccountId") String accountId,	
			@RequestBody List<ProjectDTO> lProject) {
		return new ResponseEntity<>(projectService.deletelProject(IntegraUtils.getHandleFromHeader(authorization, accountId), lProject), HttpStatus.OK);		
	}
}
