package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Application extends ObjetoPersistente {

    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="application_id")
    private List<Tecnology> tecnologies;


}
