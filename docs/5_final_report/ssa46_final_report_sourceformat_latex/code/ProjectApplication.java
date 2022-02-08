package com.buzzfeed.project;

import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.Service.NewsApiService;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.NewsApi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to Newsfeed");

        SpringApplication.run(ProjectApplication.class, args);
    }

    //Referred for data binding support from https://www.danvega.dev/blog/2017/07/05/read-json-data-spring-boot-write-database/
    @Bean
    CommandLineRunner runner(NewsApiService newsApiService, DNewsService dNewsService) {
        return args -> {


            ObjectMapper obj_mapper = new ObjectMapper();
            //Code script referred from https://www.danvega.dev/blog/2017/07/05/read-json-data-spring-boot-write-database/
            TypeReference<List<DNews>> typeRef = new TypeReference<List<DNews>>() {
            };
            InputStream input = TypeReference.class.getResourceAsStream("/json/DetailedNews.json");
            try {
                List<DNews> news1 = obj_mapper.readValue(input, typeRef);
                dNewsService.addNews(news1);
                System.out.println("News Saved with details!");
            } catch (IOException e) {
                System.out.println("Unable to save news details: " + e.getMessage());
            }
        };
    }


}
