package com.user.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email=?1 and u.password=?2")
    public User findByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.email= ?1 ")
    public User findByEmail(String email);

}
