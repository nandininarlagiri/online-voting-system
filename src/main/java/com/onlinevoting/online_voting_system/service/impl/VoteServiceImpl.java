package com.onlinevoting.online_voting_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinevoting.online_voting_system.entity.Vote;
import com.onlinevoting.online_voting_system.exception.CandidateElectionMismatchException;
import com.onlinevoting.online_voting_system.exception.ElectionNotActiveException;
import com.onlinevoting.online_voting_system.exception.VoteAlreadyCastException;
import com.onlinevoting.online_voting_system.repository.VoteRepository;
import com.onlinevoting.online_voting_system.service.VoteService;



import com.onlinevoting.online_voting_system.entity.User;
import com.onlinevoting.online_voting_system.entity.Candidate;
import com.onlinevoting.online_voting_system.entity.Election;
import com.onlinevoting.online_voting_system.entity.ElectionStatus;
import com.onlinevoting.online_voting_system.repository.UserRepository;
import com.onlinevoting.online_voting_system.repository.CandidateRepository;
import com.onlinevoting.online_voting_system.repository.ElectionRepository;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Override
   
    public Vote castVote(Vote vote) {

        User user = userRepository.findById(vote.getUser().getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Candidate candidate = candidateRepository.findById(vote.getCandidate().getId())
            .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Election election = electionRepository.findById(vote.getElection().getId())
            .orElseThrow(() -> new RuntimeException("Election not found"));

         // Rule 1: Election must be ACTIVE
        if (election.getStatus() != ElectionStatus.ACTIVE) {
            throw new ElectionNotActiveException(
                "Voting is allowed only when the election is ACTIVE.");
        }

        if (!candidate.getElection().getId().equals(election.getId())) {
            throw new CandidateElectionMismatchException(
            "Selected candidate does not belong to this election.");
        }

        if (voteRepository.existsByUserIdAndElectionId(user.getId(), election.getId())) {
            throw new VoteAlreadyCastException(
                "You have already voted in this election.");
        }
        vote.setUser(user);
        vote.setCandidate(candidate);
        vote.setElection(election);

        vote.setVoteTime(LocalDateTime.now());

        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }

}
