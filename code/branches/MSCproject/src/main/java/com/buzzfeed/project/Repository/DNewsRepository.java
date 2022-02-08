package com.buzzfeed.project.Repository;


import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.NewsApi;
import com.buzzfeed.project.domain.User;
import com.sun.org.apache.bcel.internal.generic.DNEG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DNewsRepository extends JpaRepository<DNews,Long>  {
    DNews findById(Integer id);

    @Override
    List<DNews> findAll();
}
