package com.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestStatusChangeDto {
	@NotNull(message = "ProviderId must be supplied")
    private Long providerId;
//	@NotNull(message = "Status must be supplied")
//    private InsuranceStatus status;
}
