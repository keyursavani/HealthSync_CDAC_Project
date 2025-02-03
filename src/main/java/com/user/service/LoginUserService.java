package com.user.service;

import com.user.dto.SignInDto;
import com.user.dto.SignInResponseJwtDto;
import com.user.dto.SignUpDto;


public interface LoginUserService {
 public String signUp(SignUpDto dto);
 public SignInResponseJwtDto signIn(SignInDto dto);
 public String addLoginUser(SignInResponseJwtDto dto);
}
