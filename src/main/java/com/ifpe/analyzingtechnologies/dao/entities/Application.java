package com.ifpe.analyzingtechnologies.dao.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_id")
    private List<Tecnology> tecnologies;


}
