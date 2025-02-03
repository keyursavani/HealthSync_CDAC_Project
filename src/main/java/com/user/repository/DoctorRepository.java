package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.entities.Doctor;



public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Optional<Doctor> findByEmailAndPassword(String em,String pass);
	Optional<Doctor>  findByEmail(String email);
	boolean existsByEmail(String email);
//	 @Query("select d from Doctor d join fetch d.medicalRecords where d.id=:id")
	 @Query("select d from Doctor d where d.id=:id")
	Doctor getMedicalRecords(Long id);
	
}
