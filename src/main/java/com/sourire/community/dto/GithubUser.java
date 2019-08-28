package com.sourire.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String id;
    private String login;
    private String bio;

    @Override
    public String toString() {
        return "GithubUser{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
