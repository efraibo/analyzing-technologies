package com.ifpe.analyzingtechnologies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories("com.ifpe.analyzingtechnologies.dao.repository")
public class AnalyzingTechnologiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyzingTechnologiesApplication.class, args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//
//        List<String> links = Arrays.asList("http://www.mctic.gov.br/portal",
//                "http://www.cultura.gov.br/",
//                "https://www.gov.br/mec/pt-br",
//                "https://www.mma.gov.br/",
//                "https://www.gov.br/economia/pt-br",
//                "http://www.mda.gov.br/",
//                "http://www.esporte.gov.br/",
//                "https://www.defesa.gov.br/",
//                "http://www.integracao.gov.br/",
//                "http://www.turismo.gov.br/",
//                "http://mds.gov.br/",
//                "http://www.capacidades.gov.br/");
//
//        return args -> {
////
//
////            String link = "";
//
////            for (int i = 0; i < links.size(); i++) {
////            extractValues(links.get(i));
////                System.out.println("--------------------------------------------------------- || ---------------------------------------------------------");
////            }
//
//
//        };
//    }
//
//    private void extractValues(String link) throws IOException {
//        Document document = null;
//        try {
//            document = Jsoup.connect("https://builtwith.com/?" + link)
//                    .ignoreContentType(true)
//                    .maxBodySize(0)
//                    .proxy("1.255.48.197", 8080)
//                    .timeout(5000000).get();
//
//
//            Elements boxesApplications = document.select("#mainForm > div:nth-child(3) > div > div.col-md-8.pr-1.pl-4 > div");
//
//            DomainAnalyzerJson domainAnalyzerJson = new DomainAnalyzerJson();
//
//            List<ApplicationJson> applicationsList = new ArrayList<>();
//            domainAnalyzerJson.setApplicationJsons(applicationsList);
//
//            for (Element linha : boxesApplications) {
//
//                ApplicationJson app = new ApplicationJson();
//
//                String type = linha.getElementsByClass("card-title").first().text();
//                Elements tecnologies = linha.getElementsByClass("row mb-2 mt-2");
//                app.setType(type);
//
//                List<TecnologyJson> tecList = new ArrayList<>();
//                for (Element linhaTec : tecnologies) {
//
//                    TecnologyJson tec = new TecnologyJson();
//
//                    String img = linhaTec.getElementsByTag("img").first().attr("data-src");
//                    String name = linhaTec.getElementsByTag("h2").first().text();
//
//                    tec.setIcon(img);
//                    tec.setName(name);
//
//                    tecList.add(tec);
//                }
//
//                app.setTecnologies(tecList);
//
//                applicationsList.add(app);
//
//            }
//
//            domainAnalyzerJson.setApplicationJsons(applicationsList);
//
//            Gson gson = new Gson();
//            String s = gson.toJson(domainAnalyzerJson);
//
//            System.out.println(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
