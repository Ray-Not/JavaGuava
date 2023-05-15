package ru.tinkoff.edu.java.scrapper.exception;

public class GitHubRequestException extends RuntimeException{

    public GitHubRequestException() {
    }

    public GitHubRequestException(String message) {
        super(message);
    }
}