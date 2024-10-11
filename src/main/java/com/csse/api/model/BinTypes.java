package com.csse.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BinTypes {
    private long id;
    private String name;
    private String capacity;
    private String producer;
    private String type;
}
