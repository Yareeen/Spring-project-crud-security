package com.example.rest_h2_jpa_lombok.repositories;

import com.example.rest_h2_jpa_lombok.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Roles, Long > {

    Optional<Roles> findByName(String name);
}
