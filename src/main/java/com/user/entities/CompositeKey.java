package com.user.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class CompositeKey implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(length = 30, nullable = false)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        CompositeKey that = (CompositeKey) o;
	        return Objects.equals(email, that.email) && role == that.role;
	    }
	  
	  @Override
	    public int hashCode() {
	        return Objects.hash(email, role);
	    }
}
