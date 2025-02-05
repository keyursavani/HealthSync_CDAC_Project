package com.medical.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.entities.MedicalRecord;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
//	@Query("select m from MedicalRecord m where m.patientDetails._id =:patientId")
//	List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId);

	Page<MedicalRecord> findByPatientDetails_Id(Long patientId, Pageable pageable);

	Page<MedicalRecord> findByDoctorDetails_Id(Long doctorId, Pageable pageable);
}
