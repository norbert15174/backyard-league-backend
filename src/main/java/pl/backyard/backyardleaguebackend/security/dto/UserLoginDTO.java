package pl.backyard.backyardleaguebackend.security.dto;

import lombok.Builder;

@Builder
public record UserLoginDTO(String username, String password) {

}
