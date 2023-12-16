package pl.backyard.backyardleaguebackend.api.functionality.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.backyard.backyardleaguebackend.security.dto.TokenDTO;
import pl.backyard.backyardleaguebackend.security.handler.SecurityException;
import pl.backyard.backyardleaguebackend.security.service.UserLoginService;

@SecurityException
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountSecurityController {

    private final UserLoginService userLoginService;

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return ResponseEntity.ok(userLoginService.refreshToken(httpServletRequest, httpServletResponse));
    }

}
