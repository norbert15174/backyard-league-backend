package pl.backyard.backyardleaguebackend.api.functionality.match.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchOpinionRequest;
import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchRequest;
import pl.backyard.backyardleaguebackend.api.functionality.match.specification.MatchSearchCriteria;
import pl.backyard.backyardleaguebackend.api.functionality.match.specification.MatchSpecification;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchBaseDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchOpinionDTO;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.MatchSearchService;
import pl.backyard.backyardleaguebackend.core.functionality.match.service.MatchService;
import pl.backyard.backyardleaguebackend.core.functionality.result.dto.ResultOpinionDTO;

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

    @GetMapping
    public ResponseEntity<Page<MatchBaseDTO>> get(@PageableDefault(size = 10, page = 0, sort = "matchTime", direction = Sort.Direction.DESC) Pageable pageable, MatchSearchCriteria criteria) {
        var spec = new MatchSpecification().getFilter(criteria);
        return ResponseEntity.ok(matchSearchService.getAll(spec, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(matchSearchService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDTO> opinion(@PathVariable("id") Long id, @RequestBody MatchOpinionRequest request) {
        var result = request.getResult();
        var resultDTO = ResultOpinionDTO.builder()
                .challengerScore(result.getChallengerScore())
                .challengedScore(result.getChallengedScore())
                .build();

        var dto = MatchOpinionDTO.builder()
                .id(id)
                .status(request.getStatus())
                .result(resultDTO)
                .build();

        return ResponseEntity.ok(matchService.opinion(dto));
    }

}
