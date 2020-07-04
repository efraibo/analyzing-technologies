package com.ifpe.analyzingtechnologies.service;

import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import com.ifpe.analyzingtechnologies.dao.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgaoProcessService {

    @Autowired
    private OrgaoRepository repository;

    public List<Orgao> findLinksDontProcess() {
        return repository.findByStatusFalse();
    }


}
