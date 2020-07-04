package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Tecnology extends ObjetoPersistente {
    private String name;
    private String icon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private Application application;
}
