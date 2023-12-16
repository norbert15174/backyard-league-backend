package pl.backyard.backyardleaguebackend.security.model;

import lombok.Builder;

@Builder
public record SessionModel(String clientId, String refreshToken, String shortAccessToken, String username) {

}
