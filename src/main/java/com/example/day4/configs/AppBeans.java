package com.example.day4.configs;


import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppBeans {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager=new SimpleCacheManager();
        cacheManager.setCaches(
                Arrays.asList(
                        new ConcurrentMapCache("product"),
                        new ConcurrentMapCache("note")
                )
        );
        return cacheManager;
    }




}
