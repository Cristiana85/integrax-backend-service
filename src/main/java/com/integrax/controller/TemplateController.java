package com.integrax.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.integrax.dto.ResultDTO;
import com.integrax.dto.TemplateDTO;
import com.integrax.service.TemplateService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TemplateController {

	@NonNull
	private final TemplateService templateService;

	@GetMapping(value = "templates")
	public ResponseEntity<ResultDTO<List<TemplateDTO>>> getlTemplate(
			@RequestHeader("Authorization") String authorization, 
			@RequestHeader("Accept-Language") String acceptLanguage) {
		return null;
//		return new ResponseEntity<>(projectService.getlTemplate(
//				TokenUtils.getHandleFromToken(authorization, acceptLanguage), gridId, versionId, countryCodeId), HttpStatus.OK);
	}
}
