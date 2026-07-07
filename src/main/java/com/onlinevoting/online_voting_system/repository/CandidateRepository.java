package com.onlinevoting.online_voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevoting.online_voting_system.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long >{


}
