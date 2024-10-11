package com.csse.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Resident extends User {
    protected String name;
    protected String address;
    private String residentialType;

    @OneToMany(mappedBy = "resident")
    private List<Bin> bins;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private WMA wma;

    @OneToMany(mappedBy = "resident")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "resident")
    private List<AlertNotification> notifications;
}
