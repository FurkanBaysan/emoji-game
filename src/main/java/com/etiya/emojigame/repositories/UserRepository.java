package com.etiya.emojigame.repositories;

import com.etiya.emojigame.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserName(String userName);

    @Transactional
    @Query(value = "delete from users ", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    public void deleteAllUserRecords();
}
