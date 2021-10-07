package com.job.naukri.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.naukri.demo.models.job;

@Repository
public interface jobRepository extends JpaRepository<job, Integer> {

}
