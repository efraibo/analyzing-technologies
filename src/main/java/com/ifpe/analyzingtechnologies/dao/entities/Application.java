package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Application extends ObjetoPersistente {

    private String type;

    @OneToMany
    private List<Tecnology> tecnologies;


}
