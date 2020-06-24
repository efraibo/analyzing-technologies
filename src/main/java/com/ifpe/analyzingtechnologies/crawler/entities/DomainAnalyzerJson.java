package com.ifpe.analyzingtechnologies.crawler.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainAnalyzerJson {

    private OrgaoJson orgaoJson;
    private List<ApplicationJson> applicationJsons;

}
