package com.buzzfeed.project.Service;

import com.buzzfeed.project.Repository.DNewsRepository;
import com.buzzfeed.project.Repository.NewsApiRepository;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.NewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//News Service to load different news based on user specifications.
@Service
public class DNewsService {

    @Autowired
    private DNewsRepository dNewsRepository;


    public DNews findNews(Integer id) {
        return dNewsRepository.findById(id);
    }

    //Gets all news articles from database for general newsfeed
    public Iterable<DNews> getAll() {
        return dNewsRepository.findAllByOrderByPublishedAtDesc();
    }

    //Add news to database
    public Iterable<DNews> addNews(List<DNews> news) {
        return dNewsRepository.saveAll(news);
    }

    //Find news by country
    public List<DNews> findNewsByCountry(String country) {
        return dNewsRepository.findByCountry(country);
    }

    //Service to Find news by country and category parameters
    public List<DNews> findNewsByCountryCategory(String country, String category) {
        return dNewsRepository.findByCountryCategory(country, category);
    }

    //Find news and filter based on category weight to display more or less news for each category based on their weights
    public List<DNews> findNewsByCategoryWeight(String category, Integer count) {
        return dNewsRepository.findNewsByCategoryWeight(category, count);
    }

    //Add news based to recommendation list for mentioned category
    public List<DNews> findNewsByRecommendation(List<String> category) {
        return dNewsRepository.findNewsbyrecommend(category);
    }

}
