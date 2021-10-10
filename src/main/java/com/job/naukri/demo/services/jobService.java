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
	
	
	public List<job> getJobs(String userId)
	{
		List<job> jobs= jobRepo.findAll();
		List<job> blockedJobs=userRepo.getById(Integer.parseInt(userId)).getBlocked();
		jobs.removeAll(blockedJobs);
		return jobs;
		
	}
	
	public job postJob(job j)
	{
		return jobRepo.save(j);
	}
	
	public String applyToJob(Integer userId,Integer jobId)
	{
		job jobToApply= jobRepo.getOne(jobId);
		user applier=userRepo.getById(userId);
		List<job> jobs=applier.getApplied();
		jobs.add(jobToApply);
		applier.setApplied(jobs);
		userRepo.save(applier);
		List<user> candidates=jobToApply.getCandidates();
		System.out.println(candidates);
		candidates.add(userRepo.getOne(userId));
		System.out.println(candidates);
		jobToApply.setCandidates(candidates);
		jobRepo.save(jobToApply);
		return "Job Apply Success";
		
	}
	
	public List<job> getCandidateJobs(Integer userId)
	{
		return userRepo.getById(userId).getApplied();
	}

}
