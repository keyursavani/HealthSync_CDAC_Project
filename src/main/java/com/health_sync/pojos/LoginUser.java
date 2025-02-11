package com.health_sync.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name  = "loginusers")
@Getter
@Setter
@ToString
public class LoginUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30, unique = true, nullable = false)
	private String email;
	@Column(length = 30, nullable = false)
	private String firstName;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UseRole role;
	@Column(length = 300, nullable = false)
	private String password;
}
