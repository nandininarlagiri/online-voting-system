package com.onlinevoting.online_voting_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinevoting.online_voting_system.dto.ResultDTO;
import com.onlinevoting.online_voting_system.service.ResultService;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/{electionId}")
    public List<ResultDTO> getElectionResults(@PathVariable Long electionId) {
        return resultService.getElectionResults(electionId);
    }
    

}
