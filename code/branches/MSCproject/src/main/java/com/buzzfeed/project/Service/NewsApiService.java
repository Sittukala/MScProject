package com.buzzfeed.project.Service;

import com.buzzfeed.project.Controller.NewsApiController;
import com.buzzfeed.project.Repository.NewsApiRepository;
import com.buzzfeed.project.domain.News;
import com.buzzfeed.project.domain.NewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsApiService {

    @Autowired
    private NewsApiRepository newsApiRepository;

    public NewsApi findNews(Integer id) {
        return newsApiRepository.findById(id);
    }
    public Iterable<NewsApi> getAll(){
        return newsApiRepository.findAll();
    }
    public Iterable<NewsApi> addNews(List<NewsApi> news){
        return newsApiRepository.saveAll(news);
    }

}
