package com.ifpe.analyzingtechnologies.dao.repository;

import com.ifpe.analyzingtechnologies.dao.entities.DomainAnalyzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainAnalyzerRepository extends JpaRepository<DomainAnalyzer, Long> {
}
