package com.interview.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.interview.entity.Orders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import javax.crypto.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class OrdersRepositoryTest {

  @Autowired
  private OrdersRepository ordersRepository;

  @Test void testEncrypt(){
    log.info("{}", KeyGenerators.string().generateKey());
    log.info("{}", KeyGenerators.string().generateKey());
    log.info("--------");
    String salt = KeyGenerators.string().generateKey();
    log.info("{}", salt);
    log.info("{}", Encryptors.text("123",salt).encrypt("123"));
    log.info("{}", Encryptors.text("",salt).encrypt("123"));

  }

  @Test
  public void testQuery(){

    log.info("Generate fake order by fake user.");
    Date now = new Date();
    List<Orders> ordersList = Collections.synchronizedList(new ArrayList<>());
    IntStream.range(0,23).forEach(i->{
      ordersList.add(Orders.builder().userId(1).orderDate(now).build());
    });
    List<Orders> persistedOrdersList = ordersRepository.saveAll(ordersList);
    log.info("Fake order size : {}",persistedOrdersList.size());
//    persistedOrdersList.forEach(o->{
//      log.info("oid:{}",o.getId());
//    });
    assertEquals(23,persistedOrdersList.size());
    assertEquals(23,ordersRepository.findByIdBetween(0,50, PageRequest.of(0,Integer.MAX_VALUE)).getContent().size());
    assertEquals(5,ordersRepository.findByIdBetween(0,5, PageRequest.of(0,Integer.MAX_VALUE)).getContent().size());
    assertEquals(6,ordersRepository.findByIdBetween(6,11, PageRequest.of(0,Integer.MAX_VALUE)).getContent().size());

  }
}
//    log.info("Generate fake order by fake user.");
//        Date now = new Date();
//        List<Orders> ordersList = Collections.synchronizedList(new ArrayList<>());
//    IntStream.range(0,23).forEach(i->{
//    Orders order = ordersRepository.save(Orders.builder().userId(1).orderDate(now).build());
//    ordersList.add(order);
//    });
//    log.info("Fake order size : {}",ordersList.size());
//
//    assertEquals(23,ordersList.size());
//    assertEquals(23,ordersRepository.findByIdBetween(0,50, PageRequest.of(0,Integer.MAX_VALUE)).getSize());
//    assertEquals(4,ordersRepository.findByIdBetween(0,5, PageRequest.of(0,Integer.MAX_VALUE)).getSize());
//    assertEquals(10,ordersRepository.findByIdBetween(1,11, PageRequest.of(0,Integer.MAX_VALUE)).getSize());
