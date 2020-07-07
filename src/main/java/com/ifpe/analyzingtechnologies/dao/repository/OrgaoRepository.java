package com.ifpe.analyzingtechnologies.dao.repository;

import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, Long> {
    List<Orgao> findByStatusFalse();
}
