package com.job.naukri.demo.services;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.integrator.spi.Integrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.job.naukri.demo.models.file;
import com.job.naukri.demo.models.job;
import com.job.naukri.demo.models.uploadFileResponse;
import com.job.naukri.demo.models.user;
import com.job.naukri.demo.repository.jobRepository;
import com.job.naukri.demo.repository.userRepository;

@Service
public class userService {
	
	
	
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
	
	@Autowired
	private userRepository userRepo;
	
	
	public Map<String, Object> registerUser(user u) throws NoSuchAlgorithmException
	{
		Map<String, Object> responseMap=new HashMap<String, Object>();
		String password=toHexString(getSHA(u.getPassword()));
		user toSave = new user(u.getUsername(), password, u.getRole(),u.getApplied(), u.getF());
		List<user> toFind = userRepo.findAll();
		for(user uu:toFind)
			if(u.getUsername()==toSave.getUsername())
			{
				responseMap.put("result", "user already exist");
				responseMap.put("user", u);
			}
		responseMap.put("result", "user created");
		responseMap.put("user", userRepo.save(toSave));
		return responseMap;
	}
	
	public user loginUser(user u) throws NoSuchAlgorithmException
	{
		String password=toHexString(getSHA(u.getPassword()));
		user toSave = new user(u.getUsername(), password, u.getRole(),u.getApplied(), u.getF());
		System.out.println(toSave);
		List<user> toFind = userRepo.findAll();
		for(user ue:toFind)
			System.out.println(ue);
		for(user uu:toFind)
		{
			System.out.println(uu.getUsername()+"   "+toSave.getUsername());
			if(uu.getUsername().equals(toSave.getUsername()))
				return uu;
		}
		return new user();
	}
	
	
	public List<job> getAppliedJobs(Integer id)
	{
		user user=userRepo.getById(id);
		return user.getApplied();
	}
	
	public Object getUser(Integer id)
	{
		return userRepo.findById(id);
	}
   
	public file getResume(Integer id)
	{
		user foundUser=userRepo.getById(id);
		return foundUser.getF();
		
	}
	
	public List<user> fetchUsers()
	{
		return userRepo.findAll();
	}
	
	public List<job> fetchApplied(Integer userId)
	{
		return userRepo.getById(userId).getApplied();
	}
	
	public uploadFileResponse uploadResume(MultipartFile file,Integer id) throws IOException
	{
		user toFind=userRepo.getOne(id);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		file toUpload=new file(fileName,file.getContentType(),file.getBytes());
		toFind.setF(toUpload);
		user uploaded=userRepo.save(toFind);
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("C:/Users/REESU/Documents/resumes/")
	                .path(uploaded.getF().getId())
	                .toUriString();

	        return new uploadFileResponse(uploaded.getF().getFileName(), fileDownloadUri,
	                uploaded.getF().getFileType(), file.getSize());
//		
//		 return new ByteArrayResource(uploaded.getF().getData());
	}

}
