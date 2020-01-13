package com.fon.luc.climbingRest.formData;

import lombok.Data;

@Data
public class RouteFilter {
    private String uid;
    private int pageNumber;
    private int pageSize;
    private String sortBy;
    private String direction;

    public String getUid() {
        return uid;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getDirection() {
        return direction;
    }
}
