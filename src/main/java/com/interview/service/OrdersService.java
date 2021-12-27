package com.interview.service;

import com.interview.entity.OrderDetail;
import com.interview.entity.Orders;
import com.interview.entity.Product;
import com.interview.entity.Users;
import com.interview.repository.OrdersRepository;
import com.interview.vo.OrderVO;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersService {

  @Autowired
  private OrdersRepository ordersRepository;
  @Autowired
  private UsersService usersService;
  @Autowired
  private OrderDetailService orderDetailService;

  public Integer save(Orders bean) {
    bean.setId(null);
    bean = ordersRepository.save(bean);
    return bean.getId();
  }

  public void delete(Integer id) {
    ordersRepository.deleteById(id);
  }

  public void update(Integer id, Orders vO) {
    Orders bean = requireOne(id);
    vO.setId(id);
    BeanUtils.copyProperties(vO, bean);
    ordersRepository.save(bean);
  }

  public Orders getById(Integer id) {
    return requireOne(id);
  }

  public Page<Orders> query(Orders vO) {
    throw new UnsupportedOperationException();
  }

  private Orders requireOne(Integer id) {
    return ordersRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }

  public List<Orders> findAll(PageRequest pageRequest){
    Page<Orders> recordsPage = ordersRepository.findAll(pageRequest);
    return recordsPage.getContent();
  }

  @Transactional
  public OrderVO genOrder(String username,List<OrderDetail> orderDetails){
    Users users = usersService.findByUsername(username);
    Orders orders = Orders.builder().userId(users.getId()).orderDate(new Date()).build();
    Integer id = save(orders);
    orderDetails.forEach(od->{
      od.setOrderId(id);
    });
    orderDetails = orderDetailService.saveAll(orderDetails);
    OrderVO orderVO = new OrderVO();
    orderVO.setOrders(getById(id));
    orderVO.setUsers(users);
    orderVO.setOrderDetails(orderDetails);
    return orderVO;
  }

  public List<Orders> findByIdBetween(Integer floor,Integer ceiling){
    return ordersRepository.findByIdBetween(floor, ceiling, PageRequest.of(0, Integer.MAX_VALUE)).getContent();
  }

}
