package pl.backyard.backyardleaguebackend.api.functionality.user.controller.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.ProfileDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.UserSearchService;

@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final UserSearchService userSearchService;

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfile() {
        return ResponseEntity.ok(userSearchService.getProfile());
    }

}
