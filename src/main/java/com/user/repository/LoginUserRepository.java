package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.CompositeKey;
import com.user.entities.LoginUser;


public interface LoginUserRepository extends JpaRepository<LoginUser, CompositeKey> {

//	Optional<LoginUser> findByEmailAndPassword(String em,String pass);
	Optional<LoginUser>  findById_email(String email);
	boolean existsById_email(String email);
}
