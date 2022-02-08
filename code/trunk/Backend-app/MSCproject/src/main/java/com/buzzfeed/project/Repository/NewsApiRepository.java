package com.buzzfeed.project.Repository;

import com.buzzfeed.project.domain.NewsApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsApiRepository extends JpaRepository<NewsApi, Long> {
    //default jpa methods to be overriden
    NewsApi findById(Integer id);

    @Override
    List<NewsApi> findAll();
}
