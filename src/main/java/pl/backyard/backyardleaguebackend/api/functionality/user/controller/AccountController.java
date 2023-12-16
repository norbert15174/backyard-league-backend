package pl.backyard.backyardleaguebackend.api.functionality.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.backyard.backyardleaguebackend.api.functionality.user.request.LoginRequest;
import pl.backyard.backyardleaguebackend.api.functionality.user.request.RegisterRequest;
import pl.backyard.backyardleaguebackend.api.functionality.user.response.UserResponse;
import pl.backyard.backyardleaguebackend.api.functionality.user.validator.UserLoginValidator;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.sequence.FirstAndDefaultSequence;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.RegisterDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.AccountService;
import pl.backyard.backyardleaguebackend.security.dto.TokenDTO;
import pl.backyard.backyardleaguebackend.security.dto.UserLoginDTO;
import pl.backyard.backyardleaguebackend.security.service.UserLoginService;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;
    private final UserLoginService userLoginService;
    private final UserLoginValidator userLoginValidator;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Validated(FirstAndDefaultSequence.class) @RequestBody RegisterRequest request) {
        var dto = RegisterDTO.builder()
                .username(request.getUsername())
                .name(request.getName())
                .lastname(request.getLastname())
                .password(request.getPassword())
                .email(request.getEmail())
                .roles(request.getRoles())
                .build();

        var created = accountService.register(dto);
        var response = UserResponse.builder()
                .username(request.getUsername())
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .roles(created.roles())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Validated @RequestBody LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        var dto = UserLoginDTO.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        return ResponseEntity.ok(userLoginService.login(dto, httpServletRequest, httpServletResponse));
    }

    @InitBinder("loginRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userLoginValidator);
    }

}
