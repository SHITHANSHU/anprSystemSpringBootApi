package com.anpr.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpr.demo.api.carrecord;

@Repository
public interface carrecordRep extends JpaRepository<carrecord, String>{

}
