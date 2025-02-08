package com.insurance.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.insurance.dto.InsuranceproviderDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "insuranceplans")
@Getter
@Setter
@ToString
public class InsurancePlan {
	@Id
	private String id;
	private String title;
	private long years;
	private long price;
	private String coverageDetails;
	private InsuranceproviderDto providerDetails;
}
