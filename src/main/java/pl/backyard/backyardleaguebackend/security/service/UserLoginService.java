package pl.backyard.backyardleaguebackend.security.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.backyard.backyardleaguebackend.security.dto.TokenDTO;
import pl.backyard.backyardleaguebackend.security.dto.UserLoginDTO;

public interface UserLoginService {
    TokenDTO login(UserLoginDTO dto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    TokenDTO refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
