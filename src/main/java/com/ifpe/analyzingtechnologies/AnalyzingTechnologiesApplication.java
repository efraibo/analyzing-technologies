package com.ifpe.analyzingtechnologies;

import com.ifpe.analyzingtechnologies.model.DomainAnalyzer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

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

        // request url
        String uri = "https://api.wappalyzer.com/analyze/v1/?url=https://google.com";

		// create an instance of RestTemplate
        RestTemplate template = new RestTemplate();

		// create headers
        HttpHeaders headers = new HttpHeaders();

		// set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// example of custom header
        headers.set("x-api-key", "wappalyzer.api.demo.key");

        // build the request
        HttpEntity request = new HttpEntity(headers);

		// make an HTTP GET request with headers
        ResponseEntity<DomainAnalyzer> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                request,
                DomainAnalyzer.class,
                1
        );

// check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }


        return args -> {
//			String command = "curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2";
//			String command = "curl -H x-api-key: wappalyzer.api.demo.key https://api.wappalyzer.com/analyze/v1/?url=https://google.com";
//			Process process = Runtime.getRuntime().exec(command);
//			process.getInputStream();
            try {

                URL url = new URL("https://api.wappalyzer.com/analyze/v1/?url=https://google.com");//your url i.e fetch data from .
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("x-api-key", "wappalyzer.api.demo.key");
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : "
                            + conn.getResponseCode());
                }
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (Exception e) {
                System.out.println("Exception in NetClientGet:- " + e);
            }

        };
    }

}
