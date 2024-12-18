package com.eta.repository;

import com.eta.model.User;
import com.eta.model.UserLite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    @Query("SELECT new com.eta.model.UserLite(u.username, u.password) " +
            "FROM User u WHERE u.userId = :userId")
    UserLite getUserByUserId(Long userId);
}
