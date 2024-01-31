/*package br.com.jujubaprojects.restappspringboot.serelization;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import main.java.br.com.jujubaprojects.restappspringboot.Utils.MediaType;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter{

	public YamlJackson2HttpMessageConverter() {
		super(
			new YAMLMapper()
				.setSerializationInclusion(
					JsonInclude.Include.NON_NULL),
					MediaType.parseMediaType("application/x-yaml")
				);
	}

}*/