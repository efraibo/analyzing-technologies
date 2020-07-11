package com.ifpe.analyzingtechnologies.crawler.entities;

import lombok.Data;

import java.util.List;

@Data
public class OrgaoJson {
    private Long id;
    private String nome;
    private String linkWebSite;
    private Boolean status;
    private List<ApplicationJson> applications;
}
