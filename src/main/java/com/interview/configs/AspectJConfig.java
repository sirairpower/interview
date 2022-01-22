package com.interview.configs;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;

@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
@Configuration
public class AspectJConfig {
  @Bean
  public HowardMethodAspect howardMethodAspect() {
    HowardMethodAspect aspect = Aspects.aspectOf(HowardMethodAspect.class);
    return aspect;
  }
}
