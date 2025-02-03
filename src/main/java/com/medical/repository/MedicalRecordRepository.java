package com.medical.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.medical.entities.MedicalRecord;


public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, Integer> {
// @Query("select m from MedicalRecord m join fetch m.")
//	@Query("select c from Category c left join fetch c.blogPosts where c.id=:id")
//	Category getCategoryAndPostDetails(Long id);
//	 @Query("select m from MedicalRecord m join fetch m.patientId where m.doctorId.id =:id")
//	List<MedicalRecord> getByDoctorId(Long id);
}
