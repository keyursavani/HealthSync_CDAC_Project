package com.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "insuranceproviders")
@Getter
@Setter
@ToString
public class InsuranceProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false , length = 30)
	private String companyName;
	@Column(nullable = false , length = 12)
	private long contactNumber;
	@Column(nullable = false , length = 30, unique = true)
	private String email;
	@Column(nullable = false , length = 50)
	private String address;
	@Column(length = 300, nullable = false)
	private String password;
}
