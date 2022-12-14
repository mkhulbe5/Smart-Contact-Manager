package com.smart.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
