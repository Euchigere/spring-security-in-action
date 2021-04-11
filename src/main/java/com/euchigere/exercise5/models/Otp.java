package com.euchigere.exercise5.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Otp {
    @Id
    private String username;
    private String code;
}
