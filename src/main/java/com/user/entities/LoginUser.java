package com.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "loginusers")
@Getter
@Setter
@ToString
public class LoginUser {
	@EmbeddedId
	private CompositeKey id;
	@Column(length = 30, nullable = false)
	private String firstName;
	@Column(length = 300, nullable = false)
	private String password;
}
