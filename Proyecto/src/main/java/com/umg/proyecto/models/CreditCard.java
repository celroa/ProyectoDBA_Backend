package com.umg.proyecto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private Integer id;
    private String type;
    private String ccNumber;
    private Date ccDueDate;
    private String ccName;
    private Character status;
    private Integer customerId;
}