package com.ifpe.analyzingtechnologies;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@SpringBootApplication
public class AnalyzingTechnologiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyzingTechnologiesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Document document = Jsoup.connect("https://builtwith.com/?http%3a%2f%2fwww.mctic.gov.br%2fportal")
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .proxy("1.255.48.197", 8080)
                    .timeout(5000000).get();

            Element first = document.select("#mainForm > div:nth-child(3) > div > div.col-md-8.pr-1.pl-4 > " +
                    "div:nth-child(4) > div > div.row.mb-2.mt-2 > div > h2 > a").first();


            System.out.println("Retorno: " + first.text());

        };
    }

}
