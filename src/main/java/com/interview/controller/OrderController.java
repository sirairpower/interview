package com.interview.controller;

import com.interview.entity.OrderDetail;
import com.interview.service.OrdersService;
import java.security.Principal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {


  private OrdersService ordersService;

  @Autowired
  public void setOrdersService(OrdersService ordersService) {
    this.ordersService = ordersService;
  }

  @PostMapping(value = "/create")
  @ResponseBody
  public Object orderProduct(Principal principal,@RequestBody List<OrderDetail> orderDetails){
    log.info("user name : {}",principal.getName());
    log.info("order detail size : {}",orderDetails.size());
    return ordersService.genOrder(principal.getName(),orderDetails);
  }

  @GetMapping("/findById/{floor}/{ceiling}")
  @ResponseBody
  public Object findByIdBetween(@PathVariable int floor,@PathVariable int ceiling){
    return ordersService.findByIdBetween(floor, ceiling);
  }

}
