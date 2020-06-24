package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Orgao extends ObjetoPersistente {
    private String nome;
    private String linkWebSite;
    private String status;
}
