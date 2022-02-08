package com.buzzfeed.project.Service;

import com.buzzfeed.project.Repository.DNewsRepository;
import com.buzzfeed.project.Repository.NewsApiRepository;
import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.NewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DNewsService {
    @Autowired
    private DNewsRepository dNewsRepository;

    public DNews findNews(Integer id) {
        return dNewsRepository.findById(id);
    }
    public Iterable<DNews> getAll(){
        return dNewsRepository.findAll();
    }
    public Iterable<DNews> addNews(List<DNews> news){
        return dNewsRepository.saveAll(news);
    }
}
