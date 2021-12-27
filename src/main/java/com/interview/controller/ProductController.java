package com.interview.controller;

import com.interview.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

  private ProductService productService;

  @Autowired
  public void setProductService(ProductService productService){
    this.productService = productService;
  }

  @GetMapping("/all")
  @ResponseBody
  public Object listAllProduct(){
    return productService.findAll(PageRequest.of(0, Integer.MAX_VALUE));
  }
}
