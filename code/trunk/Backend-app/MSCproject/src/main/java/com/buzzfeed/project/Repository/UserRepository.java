package com.buzzfeed.project.Repository;

import com.buzzfeed.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
//User repository to find users with different parameters.

    User findByUid(Integer uid);

    @Override
    List<User> findAll();

    User findByUserName(String userName);

}
