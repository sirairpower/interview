package com.interview.service;

import static org.junit.jupiter.api.Assertions.*;

import com.interview.entity.Orders;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class OrdersServiceTest {

  @Autowired
  private OrdersService ordersService;

  @Test
  public void testAOP(){
    log.info("testAOP");
    ordersService.save(Orders.builder().userId(50).orderDate(new Date()).build());
  }

}
