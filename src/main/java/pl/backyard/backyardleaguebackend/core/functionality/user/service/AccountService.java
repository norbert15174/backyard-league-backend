package pl.backyard.backyardleaguebackend.core.functionality.user.service;

import pl.backyard.backyardleaguebackend.core.functionality.user.dto.RegisterDTO;

public interface AccountService {
    RegisterDTO register(RegisterDTO dto);
}
