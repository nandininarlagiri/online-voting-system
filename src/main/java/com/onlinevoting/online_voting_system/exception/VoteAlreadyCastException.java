package com.onlinevoting.online_voting_system.exception;

public class VoteAlreadyCastException extends RuntimeException{
    
    public VoteAlreadyCastException(String message) {
        super(message);
    }

}
