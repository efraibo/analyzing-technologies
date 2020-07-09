package com.ifpe.analyzingtechnologies.service;

import com.google.gson.Gson;
import com.ifpe.analyzingtechnologies.crawler.entities.ApplicationJson;
import com.ifpe.analyzingtechnologies.crawler.entities.DomainAnalyzerJson;
import com.ifpe.analyzingtechnologies.crawler.entities.OrgaoJson;
import com.ifpe.analyzingtechnologies.crawler.entities.TecnologyJson;
import com.ifpe.analyzingtechnologies.dao.entities.Application;
import com.ifpe.analyzingtechnologies.dao.entities.DomainAnalyzer;
import com.ifpe.analyzingtechnologies.dao.entities.Orgao;
import com.ifpe.analyzingtechnologies.dao.repository.DomainAnalyzerRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledAnalyzer {

    private final OrgaoProcessService processService;

//    private final TecnologyMapper mapper;

    private final DomainAnalyzerRepository domainAnalyzerRepository;

    //TecnologyMapper.INSTANCE.tecnologyJsonToTecnology()

    public ScheduledAnalyzer(OrgaoProcessService processService,
                             DomainAnalyzerRepository domainAnalyzerRepository) {
        this.processService = processService;
//        this.mapper = mapper;
        this.domainAnalyzerRepository = domainAnalyzerRepository;
    }

    @Scheduled(fixedRate = 30000)
//    @Transactional
    public void processFileURLs() {

        List<Orgao> orgaos = processService.findLinksDontProcess();

        for (Orgao orgao : orgaos) {

            try {
                Document document = connectConfigurationJsoup(orgao);

                List<ApplicationJson> applicationsList = new ArrayList<>();

                DomainAnalyzerJson domainAnalyzerJson = new DomainAnalyzerJson();

                extractData(document, applicationsList, domainAnalyzerJson);

                List<Application> applications = new ArrayList<>(applicationsList.size());

                BeanUtils.copyProperties(applicationsList, applications);

//                for (int i = 0; i < applicationsList.size(); i++) {
//                    BeanUtils.copyProperties(applications.get(i), applicationsList.get(i));
//                }

//                List<Application> applications = TecnologyMapper.INSTANCE.toApplicationJsonApplications(applicationsList);

                System.out.println(applications);


                domainAnalyzerJson.setApplicationJsons(applicationsList);
                orgao.setStatus(Boolean.TRUE);

                OrgaoJson orgaoJson = new OrgaoJson();
                BeanUtils.copyProperties(orgao, orgaoJson);

//                domainAnalyzerJson.setOrgaoJson(TecnologyMapper.INSTANCE.orgaoToOrgaoJson(orgao));
                domainAnalyzerJson.setOrgaoJson(orgaoJson);

//                DomainAnalyzer domainAnalyzer = TecnologyMapper.INSTANCE.domainAnalyzerJsonToDomainAnalyzer(domainAnalyzerJson);
                DomainAnalyzer domainAnalyzer = new DomainAnalyzer();
                BeanUtils.copyProperties(domainAnalyzerJson, domainAnalyzer);
                domainAnalyzer.setOrgao(orgao);

                domainAnalyzerRepository.save(domainAnalyzer);

                Gson gson = new Gson();
                String s = gson.toJson(domainAnalyzerJson);
                System.out.println(s);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Extraindo dados via JSOUP
     * @param document
     * @param applicationsList
     * @param domainAnalyzerJson
     */
    private void extractData(Document document, List<ApplicationJson> applicationsList, DomainAnalyzerJson domainAnalyzerJson) {
        Elements boxesApplications = document.select("#mainForm > div:nth-child(3) > div > div.col-md-8.pr-1.pl-4 > div");

        domainAnalyzerJson.setApplicationJsons(applicationsList);

        for (Element linha : boxesApplications) {

            ApplicationJson app = new ApplicationJson();

            String type = linha.getElementsByClass("card-title").first().text();
            Elements tecnologies = linha.getElementsByClass("row mb-2 mt-2");
            app.setType(type);

            List<TecnologyJson> tecList = new ArrayList<>();
            for (Element linhaTec : tecnologies) {

                TecnologyJson tec = new TecnologyJson();

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
