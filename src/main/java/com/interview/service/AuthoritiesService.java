package com.interview.service;

import com.interview.entity.Authorities;
import com.interview.repository.AuthoritiesRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

  @Autowired
  private AuthoritiesRepository authoritiesRepository;

  public Integer save(Authorities bean) {
    bean.setId(null);
    bean = authoritiesRepository.save(bean);
    return bean.getId();
  }

  public void delete(Integer id) {
    authoritiesRepository.deleteById(id);
  }

  public void update(Integer id, Authorities vO) {
    Authorities bean = requireOne(id);
    vO.setId(id);
    BeanUtils.copyProperties(vO, bean);
    authoritiesRepository.save(bean);
  }

  public Authorities getById(Integer id) {
    return requireOne(id);
  }

  public Page<Authorities> query(Authorities vO) {
    throw new UnsupportedOperationException();
  }

  private Authorities requireOne(Integer id) {
    return authoritiesRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
  }
}
