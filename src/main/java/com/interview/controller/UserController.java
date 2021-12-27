package com.interview.controller;

import com.interview.entity.Users;
import com.interview.service.UsersService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

  private UsersService usersService;

  @Autowired
  public void setUsersService(UsersService usersService) {
    this.usersService = usersService;
  }

  @PostMapping("/generate-fake-user/{num}")
  @ResponseBody
  public Object generateFakeUser(@PathVariable int num){
    List<Users> usersList = Collections.synchronizedList(new ArrayList<>());
    IntStream.range(0,num).forEach(i->{
      usersList.add(Users.builder().username("user"+i).password("pass"+i).enabled(1).build());
    });
    return usersService.saveAll(usersList);
  }

  @GetMapping("/{username}")
  @ResponseBody
  public Object queryByName(@PathVariable String username){
    return usersService.findByUsername(username);
  }

  @GetMapping("/all/p{page}/s{size}")
  @ResponseBody
  public Object queryPages(@PathVariable int page,@PathVariable int size){
    return usersService.findAll(PageRequest.of(page,size));
  }

  @PostMapping("/save")
  @ResponseBody
  public Object saveUser(@RequestBody Users user){
    return usersService.save(user);
  }

  @PostMapping("/update/{id}")
  @ResponseBody
  public Object updateUser(@PathVariable int id,@RequestBody Users user){
    return usersService.update(id,user);
  }

  @PostMapping("/delete/{id}")
  @ResponseBody
  public Object deleteUser(@PathVariable int id){
    usersService.delete(id);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
