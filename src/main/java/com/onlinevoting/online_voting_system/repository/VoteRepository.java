package com.onlinevoting.online_voting_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinevoting.online_voting_system.dto.ResultDTO;
import com.onlinevoting.online_voting_system.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

    boolean existsByUserIdAndElectionId(Long userId, Long electionId);

    @Query("""
        SELECT new com.onlinevoting.online_voting_system.dto.ResultDTO(
            v.candidate.name,
            v.candidate.party,
            COUNT(v)
        )
        FROM Vote v
        WHERE v.election.id = :electionId
        GROUP BY v.candidate.id, v.candidate.name, v.candidate.party
        ORDER BY COUNT(v) DESC
    """)
    List<ResultDTO> getElectionResults(@Param("electionId") Long electionId);

}
