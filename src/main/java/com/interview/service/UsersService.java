package com.interview.service;

import com.interview.entity.Users;
import com.interview.repository.UsersRepository;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {

  @Autowired
  private UsersRepository usersRepository;

  @PostConstruct
  public void showUsers(){
    log.info("showUsers size:{}",findAll().size());
    findAll().forEach(u->{
      log.info("user id:{}",u.getId());
    });
  }

  public Integer save(Users bean) {
    bean.setId(null);
    bean = usersRepository.save(bean);
    return bean.getId();
  }

  public void delete(Integer id) {
    usersRepository.deleteById(id);
  }

  public Users update(Integer id, Users vO) {
    Users bean = requireOne(id);
    vO.setId(id);
    BeanUtils.copyProperties(vO, bean);
    return usersRepository.save(bean);
  }

  public Users getById(Integer id) {
    return requireOne(id);
  }

  public Page<Users> query(Users vO) {
    throw new UnsupportedOperationException();
  }


  private Users requireOne(Integer id) {
    return usersRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }

  public Users findByUsername(String username){
    return usersRepository.findByUsername(username);
  }

  public List<Users> findAll(){
    return usersRepository.findAll();
  }

  public List<Users> findAll(PageRequest pageRequest){
    Page<Users> recordsPage = usersRepository.findAll(pageRequest);
    return recordsPage.getContent();
  }

  public List<Users> saveAll(List<Users> usersList){
    return usersRepository.saveAll(usersList);
  }

}
