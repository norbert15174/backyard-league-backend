package pl.backyard.backyardleaguebackend.security.dto;

import lombok.Builder;

@Builder
public record TokenDTO(String accessToken, String refreshToken, String clientId) {

}
