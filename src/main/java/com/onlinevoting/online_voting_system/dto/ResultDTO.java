package com.onlinevoting.online_voting_system.dto;

public class ResultDTO {

    private String candidateName;
    private String party;
    private Long totalVotes;

    public ResultDTO() {
    }

    public ResultDTO(String candidateName, String party, Long totalVotes) {
        this.candidateName = candidateName;
        this.party = party;
        this.totalVotes = totalVotes;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

}
