package com.job.naukri.demo.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.job.naukri.demo.models.uploadFileResponse;
import com.job.naukri.demo.models.user;
import com.job.naukri.demo.services.jobService;
import com.job.naukri.demo.services.userService;

@RestController
public class userController {
	
	@Autowired
	private userService service;
	
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
	public String loginUser(@RequestBody user u) throws NoSuchAlgorithmException{
		return service.loginUser(u);
	}
	
	@PostMapping("/uploadresume")
	public uploadFileResponse uploadResume(@RequestParam("file") MultipartFile file,Integer id) throws IOException
	{
		return service.uploadResume(file,id );
	}
	
	@PostMapping("/apply")
	public String applyJob(@RequestParam Map<String, String> params)
	{
		return jobservice.applyToJob(Integer.parseInt(params.get("userId")),Integer.parseInt(params.get("jobId")));
	}
	
	

}
