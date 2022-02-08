package com.buzzfeed.project.Repository;

import com.buzzfeed.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUid(Integer uid);


    @Override
    List<User> findAll();

    User findByUserName(String userName);
}
