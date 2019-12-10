package com.etnetera.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;

import com.etnetera.hr.data.HypeLevel;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
public class JavaScriptFrameworkController {

	private final JavaScriptFrameworkRepository repository;

	@Autowired
	public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFramework> frameworks(
		@RequestParam(value="name", required=false) String name,
		@RequestParam(value="hypeLevel", required=false) HypeLevel hypeLevel
	) {
		if(name != null) {
			return repository.findByNameStartingWith(name);
		}

		if(hypeLevel != null) {
			return repository.findByHypeLevelEquals(hypeLevel);
		}

		if(name != null || hypeLevel != null) {
			return repository.findByNameStartingWithAndHypeLevelEquals(name, hypeLevel);
		}

		return repository.findAll();
	}

	@GetMapping("/framework/{id}")
	public JavaScriptFramework frameworkById(@PathVariable Long id) throws Exception {
		return repository.findById(id).get();
	}

	@PostMapping(path = "/framework", consumes = "application/json", produces = "application/json")
	public JavaScriptFramework add(@RequestBody String body) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JavaScriptFramework javascriptFramework = mapper.readValue(body, JavaScriptFramework.class);
		return repository.save(javascriptFramework);
	}

	@PutMapping(path = "/framework/{id}", consumes = "application/json", produces = "application/json")
	public void update(@PathVariable Long id, @RequestBody String body) throws Exception {
		JavaScriptFramework requestBody = new ObjectMapper().readValue(body, JavaScriptFramework.class);
		JavaScriptFramework framework = repository.findById(id).get(); 


		if(requestBody.getName() != null) {
			framework.setName(requestBody.getName());
		}

		if(requestBody.getVersion() != null) {
			framework.setVersion(requestBody.getVersion());
		}

		if(requestBody.getDeprecationDate() != null) {
			framework.setDeprecationDate(requestBody.getDeprecationDate());
		}

		if(requestBody.getHypeLevel() != null) {
			framework.setHypeLevel(requestBody.getHypeLevel());
		}

		repository.save(framework);
	}

	@DeleteMapping(path = "/framework/{id}")
	public void delete(@PathVariable Long id) throws Exception {
		repository.deleteById(id); 
	}

	@DeleteMapping(path = "/frameworks/all")
	public void deleteAll() throws Exception {
		repository.deleteAll(); 
	}
}
