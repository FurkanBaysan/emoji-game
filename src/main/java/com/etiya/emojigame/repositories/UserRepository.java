package com.etiya.emojigame.repositories;

import com.etiya.emojigame.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    public User findByUserName(String userName);
}
