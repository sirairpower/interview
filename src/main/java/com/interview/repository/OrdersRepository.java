package com.interview.repository;

import com.interview.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

  Page<Orders> findAll(Pageable pageable);

  @Query("select orders from Orders orders where orders.id between :floor and :ceiling ")
  Page<Orders> findByIdBetween(Integer floor,Integer ceiling,Pageable pageable);
}
