package com.udemy.dev.repository;

import com.udemy.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.role = ?1")
    public List<User> findUsersByRole(String role);

    @Query("SELECT u FROM User u WHERE u.role = ?1 AND u.id = ?2")
    User findUsersByRoleAndId(String role, Long id);

}
