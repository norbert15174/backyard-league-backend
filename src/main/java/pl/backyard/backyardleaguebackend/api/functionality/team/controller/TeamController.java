package pl.backyard.backyardleaguebackend.api.functionality.team.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamCreateRequest;
import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamJoinRequest;
import pl.backyard.backyardleaguebackend.api.functionality.team.specification.TeamSearchCriteria;
import pl.backyard.backyardleaguebackend.api.functionality.team.specification.TeamSpecification;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasAccessToTeam;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamCodeDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamInformationDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamLadderDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.TeamSearchService;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.TeamService;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;

@Validated
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamService teamService;
    private final TeamQueryService teamQueryService;
    private final TeamSearchService teamSearchService;


    @PostMapping
    public ResponseEntity<TeamDTO> create(@Validated @RequestBody TeamCreateRequest request) {
        return new ResponseEntity<>(teamService.create(request), HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<TeamDTO> join(@Validated @RequestBody TeamJoinRequest request) {
        return new ResponseEntity<>(teamService.join(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/code")
    public ResponseEntity<TeamCodeDTO> getCode(@UserHasAccessToTeam @PathVariable Long id) {
        return new ResponseEntity<>(teamQueryService.getCodeById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamInformationDTO> get(@PathVariable Long id) {
        return new ResponseEntity<>(teamSearchService.getTeamInformation(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<TeamLadderDTO>> getAll(@PageableDefault(size = 10, page = 0, sort = "points", direction = Sort.Direction.DESC) Pageable pageable, TeamSearchCriteria criteria) {
        var spec = new TeamSpecification().getFilter(criteria);
        return new ResponseEntity<>(teamSearchService.getAll(spec, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamInformationDTO> delete(@UserHasAccessToTeam @PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/leave")
    public ResponseEntity<TeamInformationDTO> leave(@UserHasAccessToTeam @PathVariable Long id) {
        teamService.leave(id);
        return ResponseEntity.ok().build();
    }


}
