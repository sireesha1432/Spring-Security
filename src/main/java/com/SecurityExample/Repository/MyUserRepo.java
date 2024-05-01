package com.SecurityExample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecurityExample.Model.Users;

@Repository
public interface MyUserRepo extends JpaRepository<Users, Integer> {
      Users findUserByUserName(String userName);
}
