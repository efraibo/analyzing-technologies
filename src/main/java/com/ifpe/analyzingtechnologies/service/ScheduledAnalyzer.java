package com.ifpe.analyzingtechnologies.service;

import com.google.gson.Gson;
import com.ifpe.analyzingtechnologies.crawler.entities.ApplicationJson;
import com.ifpe.analyzingtechnologies.crawler.entities.DomainAnalyzerJson;
import com.ifpe.analyzingtechnologies.crawler.entities.TecnologyJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

    public ScheduledAnalyzer(OrgaoProcessService processService) {
        this.processService = processService;
    }

    @Scheduled(fixedRate = 10000)
    public void processFileURLs() {

        List<String> links = processService.findLinksDontProcess();

        for (String link : links) {

            Document document = null;
            try {
                document = Jsoup.connect("https://builtwith.com/?" + link)
                        .ignoreContentType(true)
                        .maxBodySize(0)
                        .proxy("1.255.48.197", 8080)
                        .timeout(5000000).get();


                Elements boxesApplications = document.select("#mainForm > div:nth-child(3) > div > div.col-md-8.pr-1.pl-4 > div");

                DomainAnalyzerJson domainAnalyzerJson = new DomainAnalyzerJson();

                List<ApplicationJson> applicationsList = new ArrayList<>();
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

                domainAnalyzerJson.setApplicationJsons(applicationsList);

                Gson gson = new Gson();
                String s = gson.toJson(domainAnalyzerJson);

                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
