package com.onlinevoting.online_voting_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinevoting.online_voting_system.dto.ResultDTO;
import com.onlinevoting.online_voting_system.repository.VoteRepository;
import com.onlinevoting.online_voting_system.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public List<ResultDTO> getElectionResults(Long electionId) {
        return voteRepository.getElectionResults(electionId);
    }

}
