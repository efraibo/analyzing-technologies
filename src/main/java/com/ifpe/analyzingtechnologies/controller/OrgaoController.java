package com.ifpe.analyzingtechnologies.controller;

import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import com.ifpe.analyzingtechnologies.dao.repository.OrgaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/orgaos")
@CrossOrigin(origins = "*")
public class OrgaoController {

    @Autowired
    private OrgaoRepository orgaoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Orgao> adicionarOrgaos(@RequestBody List<Orgao> orgaos) {
        return orgaoRepository.saveAll(orgaos);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Orgao> findAllOrgao() {
        return orgaoRepository.findAll();
    }

}
