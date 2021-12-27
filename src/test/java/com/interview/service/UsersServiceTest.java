package com.interview.service;

import static org.junit.jupiter.api.Assertions.*;

import com.interview.entity.Users;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class UsersServiceTest {

  @Autowired
  private  UsersService usersService;

  @Test
  public  void testPagenation(){
    log.info("testPagenation :{}",usersService.findAll().size());
    List<Users> usersList = Collections.synchronizedList(new ArrayList<>());
    IntStream.range(0,22).forEach(i->{
      usersList.add(Users.builder().username("user"+i).password("pass"+i).enabled(1).build());
    });
    usersService.saveAll(usersList);
    //all
    assertEquals(23,usersService.findAll().size());
    //page 1-4
    assertEquals(10,usersService.findAll(PageRequest.of(0,10)).size());
    assertEquals(10,usersService.findAll(PageRequest.of(1,10)).size());
    assertEquals(3,usersService.findAll(PageRequest.of(2,10)).size());
    assertEquals(0,usersService.findAll(PageRequest.of(3,10)).size());

  }
}
