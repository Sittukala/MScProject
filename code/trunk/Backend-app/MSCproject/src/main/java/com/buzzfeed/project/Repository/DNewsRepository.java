package com.buzzfeed.project.Repository;


import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.NewsApi;
import com.buzzfeed.project.domain.User;
import com.sun.org.apache.bcel.internal.generic.DNEG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

//News Repository to query from DNEWS Database.
@Repository
public interface DNewsRepository extends JpaRepository<DNews, Long> {
    DNews findById(Integer id);

    List<DNews> findAllByOrderByPublishedAtDesc();

    //JPA native query to fetch 30 country based news where country is input parameter
    @Query(value = "SELECT * FROM DNEWS WHERE COUNTRY=:country ORDER BY PUBLISHED_AT DESC LIMIT 30", nativeQuery = true)
    List<DNews> findByCountry(String country);

    //JPA native query to fetch 10 country and category specific news
    @Query(value = "SELECT * FROM DNEWS WHERE COUNTRY=:country and CATEGORY=:category ORDER BY PUBLISHED_AT DESC LIMIT 10", nativeQuery = true)
    List<DNews> findByCountryCategory(String country, String category);

    //JPA native query to fetch limited news based on preferences by user which is measured in weights in algorithm
    @Query(value = "SELECT * FROM DNEWS WHERE CATEGORY=:category ORDER BY PUBLISHED_AT DESC LIMIT :count", nativeQuery = true)
    List<DNews> findNewsByCategoryWeight(String category, Integer count);

    //JPA native query to recommend different category news to users based on user-user similarity
    @Query(value = "SELECT * FROM DNEWS WHERE CATEGORY IN :category ORDER BY PUBLISHED_AT DESC LIMIT 10", nativeQuery = true)
    List<DNews> findNewsbyrecommend(List<String> category);


}
