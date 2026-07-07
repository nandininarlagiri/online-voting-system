package com.onlinevoting.online_voting_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinevoting.online_voting_system.entity.Candidate;
import com.onlinevoting.online_voting_system.exception.CandidateNotFoundException;
import com.onlinevoting.online_voting_system.repository.CandidateRepository;
import com.onlinevoting.online_voting_system.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidateById(Long id) {
         return candidateRepository.findById(id)
            .orElseThrow(() ->
                    new CandidateNotFoundException("Candidate not found with id: " + id));
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate candidate) {

        // Candidate existingCandidate = candidateRepository.findById(id).orElse(null);

        Candidate existingCandidate = candidateRepository.findById(id)
            .orElseThrow(() ->
                    new CandidateNotFoundException("Candidate not found with id: " + id));

        existingCandidate.setName(candidate.getName());
        existingCandidate.setParty(candidate.getParty());
        existingCandidate.setSymbol(candidate.getSymbol());
        existingCandidate.setStatus(candidate.getStatus());

        return candidateRepository.save(existingCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
        .orElseThrow(() ->
                new CandidateNotFoundException("Candidate not found with id: " + id));

        candidateRepository.delete(candidate);
    }

    

}
