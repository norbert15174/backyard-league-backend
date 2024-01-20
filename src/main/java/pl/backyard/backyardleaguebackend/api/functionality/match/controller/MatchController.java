package pl.backyard.backyardleaguebackend.api.functionality.match.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchRequest;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.MatchSearchService;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.MatchService;

@AllArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

    private final MatchService matchService;
    private final MatchSearchService matchSearchService;

    @PostMapping
    public ResponseEntity<MatchDTO> create(@RequestBody MatchRequest request) {
        return new ResponseEntity<>(matchService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(matchSearchService.getById(id));
    }

}
