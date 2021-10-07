package com.job.naukri.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.naukri.demo.models.job;
import com.job.naukri.demo.models.user;
import com.job.naukri.demo.repository.jobRepository;
import com.job.naukri.demo.repository.userRepository;

@Service
public class jobService {
	
	@Autowired
	private jobRepository jobRepo;
	
	@Autowired
	private userRepository userRepo;
	
	
	public List<job> getJobs()
	{
		return jobRepo.findAll();
	}
	
	public job postJob(job j)
	{
		return jobRepo.save(j);
	}
	
	public String applyToJob(Integer userId,Integer jobId)
	{
		job jobToApply= jobRepo.getOne(jobId);
		List<user> candidates=jobToApply.getCandidates();
		candidates.add(userRepo.getOne(userId));
		jobToApply.setCandidates(candidates);
		jobRepo.save(jobToApply);
		return "Job Apply Success";
		
	}

}
