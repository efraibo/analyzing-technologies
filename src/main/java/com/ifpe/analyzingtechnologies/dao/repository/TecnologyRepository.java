package com.ifpe.analyzingtechnologies.dao.repository;

import com.ifpe.analyzingtechnologies.dao.entities.Tecnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnologyRepository extends JpaRepository<Tecnology, Long> {
}
