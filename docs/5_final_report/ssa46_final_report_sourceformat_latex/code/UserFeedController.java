package com.buzzfeed.project.Controller;


import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.Service.UserFeedService;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.User;
import com.buzzfeed.project.domain.userfile;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import org.apache.mahout.cf.taste.similarity.UserSimilarity;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/UserFeed")
public class UserFeedController {
    //This Controller has all the functions to do with the user interests and behavior to fetch respective user feeds
    // and recommend news to users based on their similar user interests.
    @Autowired
    private UserFeedService userFeedService;

    @Autowired
    private DNewsService dNewsService;

    //Fecth user specific feeds
    @GetMapping("/{uid}")
    public ResponseEntity<List> fetchUserFeed(@PathVariable Integer uid) {
        List<DNews> news = userFeedService.fetchByUid(uid);


        // return newsApiService.getAll();
        return new ResponseEntity<List>(news, HttpStatus.OK);
    }

    //Add likes to users feed
    @PostMapping("/{uid}/liked/{category}")
    public ResponseEntity<Void> addLikedToFeed(@PathVariable Integer uid, @PathVariable String category) {
        userFeedService.addLikesToFeed(uid, category);

        // return newsApiService.getAll();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Remove likes from users feed
    @PostMapping("/{uid}/unlike/{category}")
    public ResponseEntity<Void> removeUnLiked(@PathVariable Integer uid, @PathVariable String category) {
        userFeedService.removeUnlikes(uid, category);
        // return newsApiService.getAll();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Add favorites to users feed
    @PostMapping("/{uid}/favorite/{category}")
    public ResponseEntity<Void> addFavoriteToFeed(@PathVariable Integer uid, @PathVariable String category) {
        userFeedService.addFavToFeed(uid, category);
        // return newsApiService.getAll();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    ////Remove favorites from users feed
    @PostMapping("/{uid}/unfavorite/{category}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Integer uid, @PathVariable String category) {
        userFeedService.removeFav(uid, category);
        // return newsApiService.getAll();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Add saved news to users feed
    @PostMapping("/{uid}/save/{category}")
    public ResponseEntity<Void> addToSave(@PathVariable Integer uid, @PathVariable String category) {
        userFeedService.addSaveToFeed(uid, category);
        // return newsApiService.getAll();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Delete News from saved feeds
    @PostMapping("/{uid}/delete/{category}")
    public ResponseEntity<Void> removeFromSave(@PathVariable Integer uid, @PathVariable String category) {
        userFeedService.removeSave(uid, category);
        // return newsApiService.getAll();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/recommend")
    public ResponseEntity<Resource> recommend(@RequestHeader(name = "Content-disposition") String fileName,
                                              @RequestHeader(name = "Content-Type") final String mediaType) {
        System.out.println("Downloading residents csv");
        fileName = "test.csv";
        String path = "C:/Users/sittu/IdeaProjects/MSCproject/src/main/resources/static/" + fileName;

        List<List<String>> userlist = userFeedService.recommendService();

        final InputStreamResource resource = new InputStreamResource(userFeedService.load(Collections.singletonList(userlist)));
        System.out.println("resource" + resource);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                .contentType(MediaType.parseMediaType(mediaType))
                .body(resource);
    }


    //Recommends users based on similar user interests pattern.
    @GetMapping("/csv/{uid}")
    public ResponseEntity<List> downloadUsersCSV(@PathVariable Integer uid) throws IOException {
        List<DNews> recommendedNewsList = new ArrayList<>();
        List<String> categoriesList = new ArrayList<>();
        List<List<String>> userlist = userFeedService.recommendService();
        //Writes the all users category-map information from database to csv file
        String path = "C:/Users/sittu/IdeaProjects/MSCproject/src/main/resources/static/";
        FileWriter csvWriter = new FileWriter(path + "newFile.csv");
        System.out.println("user lis" + userlist);
        for (List<String> rowData : userlist) {
            if (!rowData.get(2).equals("0.0")) {

                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
        }

        csvWriter.flush();
        csvWriter.close();

        //Fetches the user category-map information and recommends the articles to users based on
        //recommendation model which is calculated based on UserSimilarity
        try {
            //Creating data models
            DataModel datamodel = new FileDataModel(ResourceUtils.getFile("C:/Users/sittu/IdeaProjects/MSCproject/src/main/resources/static/newFile.csv")); //data

            UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(datamodel);
            UserNeighborhood neighbor = new ThresholdUserNeighborhood(0.1, usersimilarity, datamodel);
            Recommender recomender = new GenericUserBasedRecommender(datamodel, neighbor, usersimilarity);

            List<RecommendedItem> recommendations = recomender.recommend(uid, 2);

            if (recommendations != null) {
                for (RecommendedItem recommendation : recommendations) {
                    System.out.println(recommendation.getItemID());
                    System.out.println(recommendation.getValue());
                    if (recommendation.getItemID() == 1) {
                        categoriesList.add("science");
                    } else if (recommendation.getItemID() == 0) {
                        categoriesList.add("business");
                    } else if (recommendation.getItemID() == 2) {
                        categoriesList.add("health");
                    } else if (recommendation.getItemID() == 3) {
                        categoriesList.add("technology");
                    } else if (recommendation.getItemID() == 4) {
                        categoriesList.add("entertainment");
                    } else {
                        categoriesList.add("sports");
                    }


                }
            }

        } catch (Exception e) {
        }

        //Recommended list is created based on weights of categories.
        recommendedNewsList = dNewsService.findNewsByRecommendation(categoriesList);

        return new ResponseEntity<List>(recommendedNewsList, HttpStatus.CREATED);
    }
}

