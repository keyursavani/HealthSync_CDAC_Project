package com.health_sync.services;

import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.dto.SignUpDto;
import com.health_sync.pojos.LoginUser;


public interface LoginUserServices {
 public String signUp(SignUpDto dto);
 public SignInResponseJwtDto signIn(SignInDto dto);
 public String addLoginUser(SignInResponseJwtDto dto);
}
