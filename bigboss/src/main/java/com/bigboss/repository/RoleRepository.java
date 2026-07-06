package com.bigboss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigboss.entity.Role;
import com.bigboss.entity.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	 Optional<Role> findByRoleName(RoleType roleName);
}
