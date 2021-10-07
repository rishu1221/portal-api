package com.job.naukri.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.job.naukri.demo.models.job;
import com.job.naukri.demo.services.jobService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class jobController {
	
	@Autowired
	private jobService service;
	
	@PostMapping("/postjob")
	public job postJob(@RequestBody job j)
	{
		return service.postJob(j);
	}
	@GetMapping("/getjobs")
	public ResponseEntity<List<job>> getJobs()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return new ResponseEntity<List<job>>(service.getJobs(),headers,HttpStatus.OK);
		
		
	}
	
	
	

}
