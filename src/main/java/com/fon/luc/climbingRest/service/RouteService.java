package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.formData.RouteFilter;
import com.fon.luc.climbingRest.model.Route;
import com.fon.luc.climbingRest.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    public Long getFilterRouteCount(){
        return this.routeRepository.getFilterRouteCount();
    }

    public Collection<Route> getFilterRoute(RouteFilter filter) {
        Pageable firstFrom = PageRequest.of(filter.getPageNumber(), filter.getPageSize(),
                Sort.by(Sort.Direction.fromString(filter.getDirection()), filter.getSortBy()));
        return routeRepository.getFilterRoute(firstFrom);
    }

}
