package com.health_sync.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "medicalrecords")
@Getter
@Setter
@ToString( exclude = {"doctorId","patientId"})
public class MedicalRecord {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @ManyToOne
 @JoinColumn(name = "patient_id", nullable = false)
 private Patient patient;
 
 @ManyToOne
 @JoinColumn(name = "doctor_id", nullable = false)
 private Doctor doctor;
 
 @Column(nullable = false)
 private String date;
 @Column(nullable = false)
 private String record;
}
