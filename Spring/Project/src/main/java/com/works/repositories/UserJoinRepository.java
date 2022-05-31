package com.works.repositories;

import com.works.entities.UserJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJoinRepository extends JpaRepository<UserJoin, Long> {

    @Query(value = "select u.uid as id, u.name as username, u.email as useremail, r.name as rolename from user as u inner join role as r on r.id = u.uid where u.uid = ?1", nativeQuery = true)
    List<UserJoin> userJoin(long uid);

}