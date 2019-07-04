package com.equals.reader.repositories;

import com.equals.reader.models.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}