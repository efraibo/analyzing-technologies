package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Orgao extends ObjetoPersistente {
    private String nome;
    private String linkWebSite;
    private Boolean status;

    @OneToOne(mappedBy = "orgao", cascade = CascadeType.ALL)
    private DomainAnalyzer domainAnalyzer;
}
