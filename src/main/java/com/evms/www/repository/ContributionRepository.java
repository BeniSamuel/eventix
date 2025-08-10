package com.evms.www.repository;

import com.evms.www.model.Contribution;
import com.evms.www.model.Event;
import com.evms.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    List<Contribution> getContributionsByEvent (Event event);
    List<Contribution> getContributionsByContributor (User contributor);
}
