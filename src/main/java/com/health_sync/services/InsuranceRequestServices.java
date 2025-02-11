package com.health_sync.services;

import com.health_sync.dto.InsuranceRequestDto;
import com.health_sync.dto.RequestStatusChangeDto;

public interface InsuranceRequestServices {
 public String addNewRequest(InsuranceRequestDto dto);
 public String changeRequestStatus(RequestStatusChangeDto dto, Long requestId);
}
