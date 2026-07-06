package com.bigboss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigboss.entity.UserRole;
import com.bigboss.entity.UserRoleId;

@Repository
public interface UserRoleRepository
extends JpaRepository<UserRole,UserRoleId>{

}
