package com.ifpe.analyzingtechnologies.service;

import com.google.gson.Gson;
import com.ifpe.analyzingtechnologies.dao.entities.Application;
import com.ifpe.analyzingtechnologies.dao.entities.DomainAnalyzer;
import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import com.ifpe.analyzingtechnologies.dao.entities.Tecnology;
import com.ifpe.analyzingtechnologies.dao.repository.ApplicationRepository;
import com.ifpe.analyzingtechnologies.dao.repository.DomainAnalyzerRepository;
import com.ifpe.analyzingtechnologies.dao.repository.OrgaoRepository;
import com.ifpe.analyzingtechnologies.dao.repository.TecnologyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Component
public class ScheduledAnalyzer {

    private final TecnologyRepository tecnologyRepository;

    private final DomainAnalyzerRepository domainAnalyzerRepository;
    private final ApplicationRepository applicationRepository;
    private final OrgaoRepository orgaoRepository;
    @Autowired
    private OrgaoProcessService processService;

    public ScheduledAnalyzer(DomainAnalyzerRepository domainAnalyzerRepository, TecnologyRepository tecnologyRepository, ApplicationRepository applicationRepository, OrgaoRepository orgaoRepository) {
        this.domainAnalyzerRepository = domainAnalyzerRepository;
        this.tecnologyRepository = tecnologyRepository;
        this.applicationRepository = applicationRepository;
        this.orgaoRepository = orgaoRepository;
    }

    @Scheduled(fixedRate=60*60*1000)
    public void processFileURLs() {

        List<Orgao> orgaos = processService.findLinksDontProcess();

//        for (Orgao orgao : orgaos) {

        for (int i = 0; i < orgaos.size(); i++) {


            try {
                Orgao orgao = orgaos.get(i);
                Document document = connectConfigurationJsoup(orgao);

                List<Application> applicationsList = new ArrayList<Application>();

                DomainAnalyzer domainAnalyzerJson = new DomainAnalyzer();

                Orgao orgaoJson = new Orgao();

                extractData(document, applicationsList, orgaoJson);

                System.out.println(applicationsList);


                orgao.setStatus(Boolean.TRUE);

                applicationRepository.saveAll(applicationsList);
                orgao.setApplications(applicationsList);

                BeanUtils.copyProperties(orgao, orgaoJson, "applications");
                orgaoRepository.save(orgaoJson);
                domainAnalyzerJson.setOrgao(orgaoJson);

                Gson gson = new Gson();
                String s = gson.toJson(domainAnalyzerJson);
                System.out.println(s);

                domainAnalyzerRepository.save(domainAnalyzerJson);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Extraindo dados via JSOUP
     *
     * @param document
     * @param applicationsList
     * @param domainAnalyzerJson
     */
    private void extractData(Document document, List<Application> applicationsList, Orgao domainAnalyzerJson) {
        Elements boxesApplications = document.select("#mainForm > div:nth-child(3) > div > div.col-md-8.pr-1.pl-4 > div");

        domainAnalyzerJson.setApplications(applicationsList);

        for (Element linha : boxesApplications) {

            Application app = new Application();

            String type = linha.getElementsByClass("card-title").first().text();
            Elements tecnologies = linha.getElementsByClass("row mb-2 mt-2");
            app.setType(type);

            List<Tecnology> tecList = new ArrayList<>();
            for (Element linhaTec : tecnologies) {

                Tecnology tec = new Tecnology();

                String img = linhaTec.getElementsByTag("img").first().attr("data-src");
                String name = linhaTec.getElementsByTag("h2").first().text();

                tec.setIcon(img);
                tec.setName(name);

                tecList.add(tec);

            }

            app.setTecnologies(tecList);

            applicationsList.add(app);

        }
    }

    /**
     * Configuração de conexao JSoup
     * @param orgao
     * @return
     * @throws IOException
     */
    private Document connectConfigurationJsoup(Orgao orgao) throws IOException {

        return Jsoup.connect("https://builtwith.com/?" + orgao.getLinkWebSite())
                .ignoreContentType(true)
                .maxBodySize(0)
                .proxy("1.255.48.197", 8080)
                .timeout(5000000).get();
    }

}
