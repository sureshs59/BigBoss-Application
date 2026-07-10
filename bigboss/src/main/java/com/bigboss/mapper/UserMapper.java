package com.bigboss.mapper;

import org.mapstruct.Mapper;

import com.bigboss.dto.RegisterRequest;
import com.bigboss.dto.RegisterResponse;
import com.bigboss.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    RegisterResponse toResponse(User user);

}
