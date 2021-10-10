package com.job.naukri.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job.naukri.demo.models.job;
import com.job.naukri.demo.services.jobService;
@CrossOrigin("http://localhost:3000")
@RestController
public class jobController {
	
	@Autowired
	private jobService service;
	
	@PostMapping("/postjob")
	public job postJob(@RequestBody job j)
	{
		return service.postJob(j);
	}
	@GetMapping("/getjobs/{userId}")
	public List<job> getJobs(@PathVariable String userId)
	{
		return service.getJobs(userId);
		
		
	}
	@GetMapping("/getCandidateJob/{userId}")
	public List<job> getCandidateJobs(@PathVariable String userId)
	
	{
		
		return service.getCandidateJobs(Integer.parseInt(userId));
		
	}
	
	

}
