package com.spring.security.repository;

import com.spring.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
	@Query("SELECT u FROM User u WHERE u.name = ?1")
	User findByUserName(String userName);
//    public User findByUserName(String name);
}
