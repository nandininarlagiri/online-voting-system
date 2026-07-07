package com.onlinevoting.online_voting_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinevoting.online_voting_system.entity.Vote;
import com.onlinevoting.online_voting_system.service.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

     @Autowired
    private VoteService voteService;

    @PostMapping
    public Vote castVote(@RequestBody Vote vote) {
        return voteService.castVote(vote);
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/{id}")
    public Vote getVoteById(@PathVariable Long id) {
        return voteService.getVoteById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
        return "Vote deleted successfully.";
    }


}
