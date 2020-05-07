package com.anpr.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpr.demo.api.tolldata;

@Repository
public interface tolldataRep extends JpaRepository<tolldata, String>{

}
