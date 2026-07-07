package com.onlinevoting.online_voting_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinevoting.online_voting_system.entity.Election;
import com.onlinevoting.online_voting_system.service.ElectionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @PostMapping
    public Election addElection(@Valid @RequestBody Election election) {
        return electionService.addElection(election);
    }

    @GetMapping
    public List<Election> getAllElections() {
        return electionService.getAllElections();
    }

    @GetMapping("/{id}")
    public Election getElectionById(@PathVariable Long id) {
        return electionService.getElectionById(id);
    }

    @PutMapping("/{id}")
    public Election updateElection(@PathVariable Long id,
                                   @Valid @RequestBody Election election) {
        return electionService.updateElection(id, election);
    }

    @DeleteMapping("/{id}")
    public String deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
        return "Election deleted successfully.";
    }

}
