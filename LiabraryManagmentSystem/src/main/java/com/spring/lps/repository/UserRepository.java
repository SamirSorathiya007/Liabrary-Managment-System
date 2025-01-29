package com.spring.lps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.lps.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByName(String name);
}
