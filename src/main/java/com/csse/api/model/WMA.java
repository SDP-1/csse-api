package com.csse.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WMA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;

    @OneToMany(mappedBy = "wma")
    private List<Resident> residents;

    @OneToMany(mappedBy = "wma")
    private List<Route> routes;

    @OneToMany(mappedBy = "wma")
    private List<GarbageCollector> garbageCollectors;

    @OneToMany(mappedBy = "wma")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "wma")
    private List<AlertNotification> notifications;
}
