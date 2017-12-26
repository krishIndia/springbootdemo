package com.mfg.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mfg.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    @Override
	    void delete(Long id);
}
