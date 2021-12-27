package com.interview.vo;

import com.interview.entity.OrderDetail;
import com.interview.entity.Orders;
import com.interview.entity.Product;
import com.interview.entity.Users;
import java.util.List;
import lombok.Data;

@Data
public class OrderVO {

  private Orders orders;
  private Users users;
  private List<OrderDetail> orderDetails;


}
