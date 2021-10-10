package com.job.naukri.demo.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.job.naukri.demo.models.job;
import com.job.naukri.demo.models.uploadFileResponse;
import com.job.naukri.demo.models.user;
import com.job.naukri.demo.repository.jobRepository;
import com.job.naukri.demo.services.jobService;
import com.job.naukri.demo.services.userService;
@CrossOrigin("http://localhost:3000")
@RestController
public class userController {
	
	@Autowired
	private userService service;
	@Autowired 
	private jobRepository jobRepo;
	@Autowired
	private jobService jobservice;
	
	@GetMapping("/test")
	public String Test()
	{
		return "test";
	}
	
	@PostMapping(path="/register",consumes="application/json")
	public Map<String, Object> registerUser(@RequestBody user u) throws NoSuchAlgorithmException
	{
		
		return service.registerUser(u);
	}
	@PostMapping("/login")
	public user loginUser(@RequestBody user u) throws NoSuchAlgorithmException{
		return service.loginUser(u);
	}
	
	@PostMapping("/uploadresume")
	public String uploadResume(@RequestBody Map<String, String> body) throws IOException
	{
		return service.uploadResume(body);
	}
	@GetMapping("/getResume")
	public String getResume(@PathVariable String userId)
	{
		return service.getResume(userId);
	}
	
	@PostMapping("/apply")
	public String applyJob(@RequestParam Map<String, String> params)
	{
		return jobservice.applyToJob(Integer.parseInt(params.get("userId")),Integer.parseInt(params.get("jobId")));
	}
	
	@GetMapping("/all")
	public List<user> getAllUsers()
	{
		return service.fetchUsers();
	}
	
	@GetMapping("/fetchApplied/{userId}")
	public List<job> getApplied(@PathVariable String userId)
	{
		return service.getAppliedJobs(Integer.parseInt(userId));
	}
	
	@PostMapping("/updatepass")
	public String updatePassword(@RequestBody Map<String,String> body) throws NoSuchAlgorithmException
	{
		return service.updatePassword(body);
	}
	@GetMapping("/job/search/{tag}/{search}")
	public List<job> getSearch(@PathVariable String tag,@PathVariable String search)
	{
		if(tag.contentEquals("company")) {
			return jobRepo.findByCompanyContaining(search);
		}
		else if(tag.contentEquals("skills"))
		{
			return jobRepo.findBySkillsContaining(search);
		}
		
			return jobRepo.findByRoleContaining(search);
		
	}
	
	@PostMapping("/blockjob")
	public String blockJob(@RequestBody Map<String, String> body)
	{
		return service.blockJob(body);
	}
	
	@GetMapping("/getBlocked/{userId}")
	public List<job> getBlockedJobs(@PathVariable String userId)
	{
		return service.getBlockedJobs(userId);
	}
	
	
	
	

}
