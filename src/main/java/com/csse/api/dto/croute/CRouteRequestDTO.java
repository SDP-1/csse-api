package com.csse.api.dto.croute;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CRouteRequestDTO {
    private String routeName;
    private String routeDescription;
    private String startLocation;
    private String endLocation;
    private String area;
    private Date lastOptimizedDate;
    private List<Long> collectors;  // Collectors array of type long
}
