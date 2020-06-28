package com.app.controllers; 

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.app.models.Runner;
import com.app.repositories.RunnerService;

@RestController
@RequestMapping("/runners")
public class RunnerController {

	@Autowired
	RunnerService runnerService;

	@RequestMapping(method = RequestMethod.OPTIONS)
	ResponseEntity<?> options() {
	  return ResponseEntity.ok().allow(
		       HttpMethod.GET,
		       HttpMethod.POST,
		       HttpMethod.HEAD,
		       HttpMethod.OPTIONS,
		       HttpMethod.PUT,
		       HttpMethod.DELETE).
		       build(); 
	}

	@GetMapping
	public ResponseEntity<List<Runner>> getAll() {
		return ResponseEntity.ok(this.runnerService.findAll());
	}

	@PostMapping
	public ResponseEntity<Runner> insert(@RequestBody Runner runner) {
		Runner save = this.runnerService.save(runner);
		return ResponseEntity.ok(save);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Runner> find(@PathVariable("id") int id) {
		Optional<Runner> object = this.runnerService.findById(id);
		if (object.isPresent()) {
			return ResponseEntity.ok(object.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<Runner> delete(@PathVariable int id){
		Optional<Runner> object = this.runnerService.findById(id);
		if(object.isPresent()) {
			this.runnerService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<Runner> save(@PathVariable int id, @RequestBody Runner runner){
		Optional<Runner> object = this.runnerService.findById(id);
		if(object.isPresent()) {
			runner.setId(id);
			this.runnerService.save(runner);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
