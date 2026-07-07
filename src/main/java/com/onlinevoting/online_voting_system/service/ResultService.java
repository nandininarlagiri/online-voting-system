package com.onlinevoting.online_voting_system.service;

import java.util.List;

import com.onlinevoting.online_voting_system.dto.ResultDTO;

public interface ResultService {

    List<ResultDTO> getElectionResults(Long electionId);

}
