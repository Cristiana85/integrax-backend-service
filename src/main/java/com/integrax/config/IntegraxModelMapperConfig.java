package com.integrax.config;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.integrax.util.IntegraxModelMapper;

@Configuration
public class IntegraxModelMapperConfig {

	@Bean
	public IntegraxModelMapper modelMapper()
	{
		IntegraxModelMapper modelMapper = new IntegraxModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
