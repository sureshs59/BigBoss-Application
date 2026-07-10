package com.bigboss.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime createdAt;

}
