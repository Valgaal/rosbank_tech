package com.example.nikita.rosbank_tech.Models;

public class LoginResponse {
    private String accessToken;
    private UserProfileDTO userProfileDTO;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }

    public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
        this.userProfileDTO = userProfileDTO;
    }
}
