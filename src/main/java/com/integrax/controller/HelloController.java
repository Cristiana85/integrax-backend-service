package com.integrax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {


	@GetMapping(value = "hello")
	public String hello() {	
		return "Hello world";
	}
}
