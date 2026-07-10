package com.bigboss.service;

import com.bigboss.dto.LoginRequest;
import com.bigboss.dto.LoginResponse;
import com.bigboss.dto.RegisterRequest;
import com.bigboss.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
