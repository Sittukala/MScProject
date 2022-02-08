package com.buzzfeed.project.Repository;

import com.buzzfeed.project.domain.DNews;
import com.buzzfeed.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFeedRepository extends JpaRepository<DNews, Long> {


    //JPA native Query to fetch user category and weights map with user id and category name information.
    @Query(value = "select c.USER_UID, c.CATEGORY_NAME , c.MAP from USER_MAP c", nativeQuery = true)
    List<List<String>> getUsermap();

}
