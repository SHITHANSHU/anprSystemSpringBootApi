package com.anpr.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpr.demo.api.login;

@Repository
public interface loginRep extends JpaRepository<login, String>{

}
