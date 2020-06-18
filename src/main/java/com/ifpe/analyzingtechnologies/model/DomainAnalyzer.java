package com.ifpe.analyzingtechnologies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainAnalyzer {
    private String status;
    private List<Application> applications;
    private String language;

    @Override
    public String toString() {
        return "DomainAnalyzer{" +
                "status='" + status + '\'' +
                ", applications=" + applications +
                ", language='" + language + '\'' +
                '}';
    }
}
