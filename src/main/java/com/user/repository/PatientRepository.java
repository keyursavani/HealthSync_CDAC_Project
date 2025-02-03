package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.entities.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findByEmailAndPassword(String em, String pass);

	Optional<Patient> findByEmail(String email);

	boolean existsByEmail(String email);

//	@Query("select p from Patient p join fetch p.medicalRecords where p.id=:patientId")
	@Query("select p from Patient p where p.id=:patientId")
	Patient getMedicalRecords(Long patientId);
	
//	@Query("select p from Patient p join fetch p.insuranceRequests where p.id=:patientId")
	@Query("select p from Patient p where p.id=:patientId")

	Patient getInsuranceRequests(Long patientId);
}
