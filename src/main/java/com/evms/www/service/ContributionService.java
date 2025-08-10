package com.evms.www.service;

import com.evms.www.dto.ContributionDto;
import com.evms.www.enums.Status;
import com.evms.www.model.Contribution;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import com.evms.www.repository.ContributionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContributionService {
    private final ContributionRepository contributionRepository;
    private final EventService eventService;
    private final UserService userService;

    public List<Contribution> getAllContribution () {
        return contributionRepository.findAll();
    }

    public Contribution getContributionById (Long id) {
        return contributionRepository.findById(id).orElse(null);
    }

    public List<Contribution> getContributionsByEvent (Long event_id) {
        Event event = eventService.getEventById(event_id);
        if (event == null) {
            return null;
        }
        return contributionRepository.getContributionsByEvent(event);
    }

    public List<Contribution> getContributionsByContributor (Long contributor_id) {
        User contributor = userService.getUserById(contributor_id);
        if (contributor == null) {
            return null;
        }
        return contributionRepository.getContributionsByContributor(contributor);
    }

    public Contribution createContribution (ContributionDto contributionDto) {
        Event event = eventService.getEventById(contributionDto.getEvent_id());
        if (event == null) {
            return null;
        }

        User contributor = userService.getUserById(contributionDto.getContributor_id());
        if (contributor == null) {
            return null;
        }

        if (event.getStatus().equals(Status.CLOSED)) {
            return null;
        }

        Contribution newContribution = new Contribution(event, contributionDto.getAmount(), contributor, contributionDto.getDescription(), contributionDto.getProvided_at());
        return contributionRepository.save(newContribution);
    }

    public Contribution updateContributionById (Long id, ContributionDto contributionDto) {
        Contribution contribution = getContributionById(id);
        if (contribution == null) {
            return null;
        }

        User contributor = userService.getUserById(contributionDto.getContributor_id());
        if (contributor == null) {
            return null;
        }

        Event event = eventService.getEventById(contributionDto.getEvent_id());
        if (event == null) {
            return null;
        }

        contribution.setAmount(contributionDto.getAmount());
        contribution.setContributor(contributor);
        contribution.setEvent(event);
        contribution.setDescription(contributionDto.getDescription());
        contribution.setProvided_at(contributionDto.getProvided_at());

        return contributionRepository.save(contribution);
    }

    public Boolean deleteContributionById (Long id) {
        Contribution contribution = getContributionById(id);
        if (contribution != null) {
            contributionRepository.delete(contribution);
            return true;
        }
        return false;
    }
}
