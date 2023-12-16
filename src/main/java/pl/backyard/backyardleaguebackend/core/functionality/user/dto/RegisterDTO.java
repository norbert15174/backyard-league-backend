package pl.backyard.backyardleaguebackend.core.functionality.user.dto;

import lombok.Builder;

import java.util.Collection;

@Builder
public record RegisterDTO(String username, String password, String email, String name, String lastname, Collection<String> roles) {

}
