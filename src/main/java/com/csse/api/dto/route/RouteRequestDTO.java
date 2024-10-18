package com.csse.api.dto.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestDTO {
    private String routeName;
    private String routeDescription;
    private String startLocation;
    private String endLocation;
    private String area;
    private Date lastOptimizedDate;
    private long wmaId;
}
