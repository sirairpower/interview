package com.interview.repository;

import com.interview.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer>, JpaSpecificationExecutor<Authorities> {

}
