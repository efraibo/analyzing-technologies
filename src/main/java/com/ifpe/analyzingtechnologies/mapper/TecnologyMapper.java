package com.ifpe.analyzingtechnologies.mapper;

import com.ifpe.analyzingtechnologies.crawler.entities.ApplicationJson;
import com.ifpe.analyzingtechnologies.crawler.entities.DomainAnalyzerJson;
import com.ifpe.analyzingtechnologies.crawler.entities.OrgaoJson;
import com.ifpe.analyzingtechnologies.crawler.entities.TecnologyJson;
import com.ifpe.analyzingtechnologies.dao.entities.Application;
import com.ifpe.analyzingtechnologies.dao.entities.DomainAnalyzer;
import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import com.ifpe.analyzingtechnologies.dao.entities.Tecnology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TecnologyMapper {

    Tecnology tecnologyJsonToTecnology(TecnologyJson tecnologyJson);
    Application applicationJsonToApplication(ApplicationJson applicationJson);
    OrgaoJson orgaoToOrgaoJson(Orgao orgao);
    List<Application> toApplicationJsonApplications(List<ApplicationJson> applicationsJson);


    @Mapping(source = "orgaoJson", target = "orgao")
    @Mapping(source = "applicationJsons", target = "applications")
    DomainAnalyzer domainAnalyzerJsonToDomainAnalyzer(DomainAnalyzerJson domainAnalyzerJson);




}
