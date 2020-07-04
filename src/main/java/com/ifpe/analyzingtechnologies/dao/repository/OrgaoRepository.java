package com.ifpe.analyzingtechnologies.dao.repository;

import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgaoRepository extends JpaRepository<Orgao, Long> {
    List<Orgao> findByStatusFalse();
}
