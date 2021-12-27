package com.interview.vo;

import com.interview.entity.OrderDetail;
import com.interview.entity.Product;
import lombok.Data;

@Data
public class OrderDetailVO extends OrderDetail {
  private Product product;
}
