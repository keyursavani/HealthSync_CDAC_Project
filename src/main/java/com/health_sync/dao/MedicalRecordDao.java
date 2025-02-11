package com.health_sync.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.health_sync.pojos.MedicalRecord;

public interface MedicalRecordDao extends JpaRepository<MedicalRecord, Long> {
// @Query("select m from MedicalRecord m join fetch m.")
//	@Query("select c from Category c left join fetch c.blogPosts where c.id=:id")
//	Category getCategoryAndPostDetails(Long id);
//	 @Query("select m from MedicalRecord m join fetch m.patientId where m.doctorId.id =:id")
//	List<MedicalRecord> getByDoctorId(Long id);
}
