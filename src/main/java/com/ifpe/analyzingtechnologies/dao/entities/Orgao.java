package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Orgao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String linkWebSite;
    private Boolean status;

    @OneToMany()
    @JoinColumn(name = "orgao_id")
    private List<Application> applications;
}
