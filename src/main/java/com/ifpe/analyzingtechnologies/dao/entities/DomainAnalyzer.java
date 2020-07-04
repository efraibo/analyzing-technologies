package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class DomainAnalyzer extends ObjetoPersistente {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orgao_id", referencedColumnName = "id")
    private Orgao orgao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Application> applications;

}
