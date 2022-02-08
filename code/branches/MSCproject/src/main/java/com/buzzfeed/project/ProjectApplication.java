package com.buzzfeed.project;

import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.Service.NewsApiService;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.NewsApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
       System.out.println("Welcome to Newsfeed");
        SpringApplication.run(ProjectApplication.class, args);
    }

    //Taken for data binding support from https://www.danvega.dev/blog/2017/07/05/read-json-data-spring-boot-write-database/
    @Bean
    CommandLineRunner runner(NewsApiService newsApiService, DNewsService dNewsService) {
        return args -> {
            // read json and write to db
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<List<NewsApi>> typeReference = new TypeReference<List<NewsApi>>(){};
//            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/newstest.json");
//            try {
//                List<NewsApi> news = mapper.readValue(inputStream,typeReference);
//                newsApiService.addNews(news);
//                System.out.println("News Saved!");
//            } catch (IOException e){
//                System.out.println("Unable to save news: " + e.getMessage());
//            }

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<NewsApi>> typeReference = new TypeReference<List<NewsApi>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/newstest.json");
            try {
                List<NewsApi> news = mapper.readValue(inputStream,typeReference);
                newsApiService.addNews(news);
                System.out.println("News Saved!");
            } catch (IOException e){
                System.out.println("Unable to save news: " + e.getMessage());
            }

            TypeReference<List<DNews>> typeReference1 = new TypeReference<List<DNews>>(){};
            InputStream inputStream1 = TypeReference.class.getResourceAsStream("/json/DetailedNews.json");
            try {
                List<DNews> news1 = mapper.readValue(inputStream1,typeReference1);
                dNewsService.addNews(news1);
                System.out.println("News Saved with details!");
            } catch (IOException e){
                System.out.println("Unable to save news details: " + e.getMessage());
            }
        };
    }



}
