package com.anpr.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpr.demo.api.numberplate;

@Repository
public interface numberplateRep extends JpaRepository<numberplate, String>{

}
