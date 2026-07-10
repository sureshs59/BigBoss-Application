package com.bigboss.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponse {

    private Long id;

    private String fullName;

    private String email;

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;

}