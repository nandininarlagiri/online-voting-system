package com.onlinevoting.online_voting_system.service;

import java.util.List;

import com.onlinevoting.online_voting_system.entity.Candidate;
// import com.onlinevoting.online_voting_system.repository.CandidateRepository;
import com.onlinevoting.online_voting_system.service.CandidateService;

public interface CandidateService {

     Candidate addCandidate(Candidate candidate);

    List<Candidate> getAllCandidates();

    Candidate getCandidateById(Long id);

    Candidate updateCandidate(Long id, Candidate candidate);

    void deleteCandidate(Long id);

}
