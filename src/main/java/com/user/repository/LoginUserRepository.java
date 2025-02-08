package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.LoginUser;


public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

	Optional<LoginUser> findByEmailAndPassword(String em,String pass);
	Optional<LoginUser>  findByEmail(String email);
	boolean existsByEmail(String email);
//	void deleteByEmail(String email);
}
