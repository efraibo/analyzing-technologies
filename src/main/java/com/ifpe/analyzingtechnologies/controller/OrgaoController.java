package com.ifpe.analyzingtechnologies.controller;

import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import com.ifpe.analyzingtechnologies.dao.repository.OrgaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/orgaos")
@CrossOrigin(origins = "*")
public class OrgaoController {

    private final OrgaoRepository orgaoRepository;

    public OrgaoController(OrgaoRepository orgaoRepository) {
        this.orgaoRepository = orgaoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Orgao> adicionarOrgaos(@RequestBody List<Orgao> orgaos) {
        return orgaoRepository.saveAll(orgaos);
    }

}
