package com.spring.api.springbootmapping.repository;

import com.spring.api.springbootmapping.entity.Barend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarendRepository extends JpaRepository<Barend,Long> {

    public Barend save(Barend bar);
}
