package pl.backyard.backyardleaguebackend.core.functionality.user.dto;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String fullName, String username, String email) {
}
