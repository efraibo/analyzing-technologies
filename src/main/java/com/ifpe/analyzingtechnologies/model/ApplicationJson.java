package com.ifpe.analyzingtechnologies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationJson {
    private String name;
    private String confidence;
    private String icon;
    private String version;
    private String website;
    private HashMap<String, String> categories;

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", confidence='" + confidence + '\'' +
                ", icon='" + icon + '\'' +
                ", version='" + version + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
