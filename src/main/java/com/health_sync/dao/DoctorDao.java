package com.health_sync.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.health_sync.pojos.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {
	
	Optional<Doctor> findByEmailAndPassword(String em,String pass);
	Optional<Doctor>  findByEmail(String email);
	boolean existsByEmail(String email);
	 @Query("select d from Doctor d join fetch d.medicalRecords where d.id=:id")
	Doctor getMedicalRecords(Long id);
	
}
