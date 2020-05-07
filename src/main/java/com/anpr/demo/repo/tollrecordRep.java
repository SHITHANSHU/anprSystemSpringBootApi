package com.anpr.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpr.demo.api.tollrecord;

@Repository
public interface tollrecordRep extends JpaRepository<tollrecord, String>{

}
