package com.interview.service;

import com.interview.entity.Product;
import com.interview.entity.Users;
import com.interview.repository.ProductRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Integer save(Product bean) {
    bean.setId(null);
    bean = productRepository.save(bean);
    return bean.getId();
  }

  public void delete(Integer id) {
    productRepository.deleteById(id);
  }

  public void update(Integer id, Product vO) {
    Product bean = requireOne(id);
    vO.setId(id);
    BeanUtils.copyProperties(vO, bean);
    productRepository.save(bean);
  }

  public Product getById(Integer id) {
    return requireOne(id);
  }

  public Page<Product> query(Product vO) {
    throw new UnsupportedOperationException();
  }

  private Product requireOne(Integer id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }

  public List<Product> findAll(PageRequest pageRequest){
    Page<Product> recordsPage = productRepository.findAll(pageRequest);
    return recordsPage.getContent();
  }
}
