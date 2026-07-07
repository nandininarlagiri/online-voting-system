package com.onlinevoting.online_voting_system.exception;

public class ElectionNotActiveException extends RuntimeException{

    public ElectionNotActiveException(String message) {
        super(message);
    }

}
