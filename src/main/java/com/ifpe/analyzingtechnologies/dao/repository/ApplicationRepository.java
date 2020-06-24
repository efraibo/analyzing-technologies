package com.ifpe.analyzingtechnologies.dao.repository;

import com.ifpe.analyzingtechnologies.dao.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
