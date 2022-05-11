package com.tietoevry.backend.user;

import com.tietoevry.backend.user.model.Role;
import com.tietoevry.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Optional<List<Role>> findAllByName(String name);
}
