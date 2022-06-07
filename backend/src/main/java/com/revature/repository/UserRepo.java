package com.revature.repository;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public List<User> findAll();
    public User findByEmailAndPassword( String email, String password );
    public User findByUserId ( int id );

}
