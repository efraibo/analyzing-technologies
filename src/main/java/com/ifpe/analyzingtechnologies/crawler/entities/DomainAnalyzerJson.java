package com.ifpe.analyzingtechnologies.crawler.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainAnalyzerJson {
    private Long id;
    private OrgaoJson orgao;
//    private List<ApplicationJson> applications;

}
