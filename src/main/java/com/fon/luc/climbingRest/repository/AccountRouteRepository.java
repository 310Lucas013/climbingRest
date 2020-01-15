package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.AccountRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AccountRouteRepository extends JpaRepository<AccountRoute, Long> {

    @Query(value ="SELECT ar.id, ar.account_id, ar.attempt_date, ar.route_id, ar.zone_reached  " +
            "FROM account_route ar INNER JOIN [route] r ON ar.route_id = r.route_id INNER JOIN [route_competition] rc ON r.route_id = rc.route_id " +
            "WHERE rc.competition_id = :competitionId", nativeQuery = true)
    List<AccountRoute> getAccountRoutesByCompetitionId(@Param("competitionId") Long CompetitionId);

    int countAccountRouteByAccount_Email(String email);
}
