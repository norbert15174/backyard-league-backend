package pl.backyard.backyardleaguebackend.core.functionality.user.service;

import pl.backyard.backyardleaguebackend.core.functionality.user.dto.ProfileDTO;

public interface UserSearchService {
    ProfileDTO getProfile();

    ProfileDTO getUserInfo(Long id);
}
