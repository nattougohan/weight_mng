package com.practice.weightMng.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.weightMng.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
