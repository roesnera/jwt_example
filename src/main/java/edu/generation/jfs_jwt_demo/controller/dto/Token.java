package edu.generation.jfs_jwt_demo.controller.dto;

public record Token(String accessToken) {
    public String getAccessToken() {return accessToken;}
}
