package com.medical.entities;



import org.springframework.data.mongodb.core.mapping.Document;

import com.medical.dto.DoctorDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Document(collection = "medicalrecord") 
@Getter
@Setter
@ToString
public class MedicalRecord {
 private Long patientId;
 private DoctorDto doctorDetails;
 private String date;
 private String prescription;
 private String image;
}
