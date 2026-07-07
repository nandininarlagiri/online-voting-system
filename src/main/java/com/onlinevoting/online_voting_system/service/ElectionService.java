package com.onlinevoting.online_voting_system.service;

import java.util.List;

import com.onlinevoting.online_voting_system.entity.Election;

public interface ElectionService {

     Election addElection(Election election);

    List<Election> getAllElections();

    Election getElectionById(Long id);

    Election updateElection(Long id, Election election);

    void deleteElection(Long id);

}
