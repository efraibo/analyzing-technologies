package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Tecnology extends ObjetoPersistente {
    private String name;
    private String icon;
}
