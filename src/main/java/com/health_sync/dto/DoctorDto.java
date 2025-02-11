package com.health_sync.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoctorDto {
	private Long id;
	private long doctorId;
	private String firstName;
	private String lastName;
    private String specialization;
	private long contactNumber;
	private String email;
	private String address;
}
