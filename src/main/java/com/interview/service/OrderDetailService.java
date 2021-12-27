package com.interview.service;

import com.interview.entity.OrderDetail;
import com.interview.entity.Users;
import com.interview.repository.OrderDetailRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  public Integer save(OrderDetail bean) {
    bean.setId(null);
    bean = orderDetailRepository.save(bean);
    return bean.getId();
  }

  public void delete(Integer id) {
    orderDetailRepository.deleteById(id);
  }

  public void update(Integer id, OrderDetail vO) {
    OrderDetail bean = requireOne(id);
    vO.setId(id);
    BeanUtils.copyProperties(vO, bean);
    orderDetailRepository.save(bean);
  }

  public OrderDetail getById(Integer id) {
    return requireOne(id);
  }

  public Page<OrderDetail> query(OrderDetail vO) {
    throw new UnsupportedOperationException();
  }


  private OrderDetail requireOne(Integer id) {
    return orderDetailRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }

  public List<OrderDetail> saveAll(List<OrderDetail> orderDetails){
    return orderDetailRepository.saveAll(orderDetails);
  }
}
