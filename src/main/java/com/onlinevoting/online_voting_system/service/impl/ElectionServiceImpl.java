package com.onlinevoting.online_voting_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinevoting.online_voting_system.entity.Election;
import com.onlinevoting.online_voting_system.repository.ElectionRepository;
import com.onlinevoting.online_voting_system.service.ElectionService;


@Service
public class ElectionServiceImpl implements ElectionService{

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public Election addElection(Election election) {
        return electionRepository.save(election);
    }

    @Override
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    @Override
    public Election getElectionById(Long id) {
        return electionRepository.findById(id).orElse(null);
    }

    @Override
    public Election updateElection(Long id, Election election) {

        Election existingElection = electionRepository.findById(id).orElse(null);

        if (existingElection != null) {

            existingElection.setTitle(election.getTitle());
            existingElection.setDescription(election.getDescription());
            existingElection.setStartDate(election.getStartDate());
            existingElection.setEndDate(election.getEndDate());
            existingElection.setStatus(election.getStatus());

            return electionRepository.save(existingElection);
        }

        return null;
    }

    @Override
    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }


}
