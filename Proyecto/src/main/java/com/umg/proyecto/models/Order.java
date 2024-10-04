package com.umg.proyecto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private Date purchaseDate;
    private Integer customerId;
    private Float total;
}