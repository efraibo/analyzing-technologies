package com.ifpe.analyzingtechnologies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainAnalyzerJson {
    private String status;
    private List<ApplicationJson> applicationJsons;
    private String language;

    @Override
    public String toString() {
        return "DomainAnalyzer{" +
                "status='" + status + '\'' +
                ", applications=" + applicationJsons +
                ", language='" + language + '\'' +
                '}';
    }
}
