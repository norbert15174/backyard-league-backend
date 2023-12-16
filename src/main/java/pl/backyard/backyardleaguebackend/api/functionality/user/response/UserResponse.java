package pl.backyard.backyardleaguebackend.api.functionality.user.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record UserResponse(String username, String email, String name, String lastname, String createdAt,
                           Collection<String> roles) {
}
