package com.buzzfeed.project.Controller;

import com.buzzfeed.project.Service.DNewsService;
import com.buzzfeed.project.Service.NewsApiService;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.News;
import com.buzzfeed.project.domain.NewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/News")
public class NewsApiController {

//    @Autowired
//    private NewsApiService newsApiService;

    @Autowired
    private DNewsService dNewsService;

    //Tested with sample inpute news data initial level
    News n1 = new News(1, "News1", "Finland Vs Belgium", "Sports", "Pertersburg", "Sports column");
    News n2 = new News(2, "News2", "Finland Vs Australia", "Sports Football", "Finland", "Sports Direct");


    @GetMapping("/")
    public String greet() {
        return "Hello, welcome to news feed Api";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/list")
    public List<News> newslist() {
        List<News> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        return list;
    }

    //Get News from News Service which fecthed from database.
    @GetMapping("/news")
    public ResponseEntity<Iterable> list() {
        Iterable<DNews> news = dNewsService.getAll();
        return new ResponseEntity<Iterable>(news, HttpStatus.OK);
    }


    //Get Location based news
    @GetMapping("/location/{country}")
    public ResponseEntity<Iterable> getLocNews(@PathVariable String country) {
        Iterable<DNews> news = dNewsService.findNewsByCountry(country);
        return new ResponseEntity<Iterable>(news, HttpStatus.OK);
    }

    //Get category and country specific news
    @GetMapping("/cateloc/{country}/{category}")
    public ResponseEntity<Iterable> getnews(@PathVariable String country, @PathVariable String category) {
        Iterable<DNews> news = dNewsService.findNewsByCountryCategory(country, category);
        return new ResponseEntity<Iterable>(news, HttpStatus.OK);
    }
}
