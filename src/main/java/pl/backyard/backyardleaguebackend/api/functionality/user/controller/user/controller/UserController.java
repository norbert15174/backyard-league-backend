package pl.backyard.backyardleaguebackend.api.functionality.user.controller.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.ProfileDTO;
import pl.backyard.backyardleaguebackend.core.functionality.user.mapper.ProfileMapper;
import pl.backyard.backyardleaguebackend.core.functionality.user.dto.UserDTO;
import pl.backyard.backyardleaguebackend.api.functionality.user.controller.user.specification.UserSearchCriteria;
import pl.backyard.backyardleaguebackend.api.functionality.user.controller.user.specification.UserSpecification;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.UserSearchService;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserQueryService userQueryService;
    private final UserSearchService userSearchService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(@PageableDefault(size = 10, page = 0, sort = "username", direction = Sort.Direction.ASC) Pageable pageable, UserSearchCriteria criteria) {
        var filter = new UserSpecification().getFilter(criteria);
        return ResponseEntity.ok(ProfileMapper.map(userQueryService.getAll(filter, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userSearchService.getUserInfo(id));
    }

}
