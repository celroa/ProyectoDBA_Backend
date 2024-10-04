package com.umg.proyecto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String description;
    private Character status;
}