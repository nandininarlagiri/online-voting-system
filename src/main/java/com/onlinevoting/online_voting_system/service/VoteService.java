package com.onlinevoting.online_voting_system.service;

import java.util.List;

import com.onlinevoting.online_voting_system.entity.Vote;

public interface VoteService {

    Vote castVote(Vote vote);

    List<Vote> getAllVotes();

    Vote getVoteById(Long id);

    void deleteVote(Long id);

}
