package com.fullstack.B_Airways_Backend.repo;
import com.fullstack.B_Airways_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM USER WHERE EMAIL = ?1",nativeQuery = true)  // ?1: palaweni value eka
    User getUserByEmail(String email);

    @Query(value = "SELECT * FROM USER WHERE USERID = ?1",nativeQuery = true)  // ?1: palaweni value eka
    User getUserByUserID(String userID);

    List<User> findByEmail(String email);
}
