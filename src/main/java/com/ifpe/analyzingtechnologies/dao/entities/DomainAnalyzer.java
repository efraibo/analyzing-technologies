package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class DomainAnalyzer extends ObjetoPersistente {

    private String siteUrl;

    @OneToOne
    private Application applicationJsons;

}
