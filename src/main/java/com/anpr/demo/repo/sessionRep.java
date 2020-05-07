package com.anpr.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpr.demo.api.session;

@Repository
public interface sessionRep extends JpaRepository<session, String>{

}
