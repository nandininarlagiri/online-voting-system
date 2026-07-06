package com.onlinevoting.online_voting_system.repository;

import com.onlinevoting.online_voting_system.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    boolean existsByEmail(String email);

}
