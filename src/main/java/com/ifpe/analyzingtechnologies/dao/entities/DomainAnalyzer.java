package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class DomainAnalyzer extends ObjetoPersistente {

    @OneToOne
    private Orgao orgao;

    @OneToMany
    private List<Application> applicationJsons;

}
