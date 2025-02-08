package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.InsuranceProvider;

public interface InsuranceProviderRepository extends JpaRepository<InsuranceProvider, Long> {
	Optional<InsuranceProvider> findByEmailAndPassword(String em,String pass);
	Optional<InsuranceProvider>  findByEmail(String email);
	boolean existsByEmail(String email);
}
