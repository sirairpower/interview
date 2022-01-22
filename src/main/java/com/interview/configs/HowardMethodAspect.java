package com.interview.configs;

import com.interview.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class HowardMethodAspect {

  @Pointcut("execution(* org.springframework.data.jpa.repository.support.SimpleJpaRepository.save(..)) && args(entity)")
  public void executeSave(Orders entity) {
  }

  @Around("executeSave(entity)")
  public Object around(ProceedingJoinPoint pjp,Object entity) throws Throwable {
    log.info("bef execution aspectj SimpleJpaRepository : {}",pjp.getArgs().length);
    return pjp.proceed();
  }

//  @Around("execution(* com.interview.service.OrdersService.save(..))")
//  public Object aroundClass(ProceedingJoinPoint pjp) throws Throwable {
//    log.info("bef execution aspectj OrdersService : "+pjp.getArgs().length);
//    return pjp.proceed();
//
//  }
}
