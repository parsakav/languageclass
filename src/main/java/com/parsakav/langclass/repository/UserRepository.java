package com.parsakav.langclass.repository;

import com.parsakav.langclass.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsername(String username);
    @Query("select u from User u where u.id=:idd")
    public User selectById(@Param("idd")long idd);



}
