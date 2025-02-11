package com.health_sync.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health_sync.pojos.LoginUser;

public interface LoginUserDao extends JpaRepository<LoginUser, Long> {

	Optional<LoginUser> findByEmailAndPassword(String em,String pass);
	Optional<LoginUser>  findByEmail(String email);
	boolean existsByEmail(String email);
}
