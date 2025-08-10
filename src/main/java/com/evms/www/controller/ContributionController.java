package com.evms.www.controller;

import com.evms.www.dto.ContributionDto;
import com.evms.www.model.Contribution;
import com.evms.www.service.ContributionService;
import com.evms.www.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evms/contribution")
@AllArgsConstructor
public class ContributionController {
    private final ContributionService contributionService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Contribution>>> getAllContributions () {
        return ApiResponse.ok("Successfully obtained all contributions!!! 🎉🎉🎉", contributionService.getAllContribution());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Contribution>> getContributionById (@PathVariable Long id) {
        Contribution contribution = contributionService.getContributionById(id);
        if (contribution != null) {
            return ApiResponse.ok("Successfully obtained a contribution!!! 🎉🎉🎉", contribution);
        }
        return ApiResponse.notFound("Failed to obtain a contribution not found!!! 💔😔😔", null);
    }

    @GetMapping("/contributor/{contributor_id}")
    public ResponseEntity<ApiResponse<List<Contribution>>> getContributionByContributor (@PathVariable Long contributor_id) {
        List<Contribution> contributions = contributionService.getContributionsByContributor(contributor_id);
        if (!contributions.isEmpty()) {
            return ApiResponse.ok("Successfully obtained contribution by contributor!!! 🎉🎉🎉", contributions);
        }
        return ApiResponse.notFound("Failed to obtain contribution by contributor!!! 💔😔😔", null);
    }

    @GetMapping("/event/{event_id}")
    public ResponseEntity<ApiResponse<List<Contribution>>> getContributionsByEvent (@PathVariable Long event_id) {
        List<Contribution> contributions = contributionService.getContributionsByEvent(event_id);
        if (!contributions.isEmpty()) {
            return ApiResponse.ok("Successfully obtained contribution by event!!! 🎉🎉🎉", contributions);
        }
        return ApiResponse.notFound("Failed to obtain contribution by event!!! 💔😔😔", null);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Contribution>> createContribution (@RequestBody ContributionDto contributionDto) {
        Contribution newContribution = contributionService.createContribution(contributionDto);
        if (newContribution != null) {
            return ApiResponse.created("Successfully created contribution!!! 🎉🎉🎉", newContribution);
        }
        return ApiResponse.badRequest("Failed to create contribution bad request!!! 💔😔😔", null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Contribution>> updateContributionById (@PathVariable Long id, @RequestBody ContributionDto contributionDto) {
        Contribution contribution = contributionService.updateContributionById(id, contributionDto);
        if (contribution != null) {
            return ApiResponse.ok("Successfully updated contribution!!! 🎉🎉🎉", contribution);
        }
        return ApiResponse.notFound("Failed to update contribution!!!", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteContribution (@PathVariable Long id) {
        return contributionService.deleteContributionById(id) ?
                ApiResponse.ok("Successfully delete contribution!!! 🎉🎉🎉", true) :
                ApiResponse.notFound("Failed to delete contribution!!! 💔😔😔", false);
    }
}
