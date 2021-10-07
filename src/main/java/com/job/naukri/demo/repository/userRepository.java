package com.job.naukri.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.naukri.demo.models.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer> {

}
