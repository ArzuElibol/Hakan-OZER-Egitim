package com.example.day4.services;

import com.example.day4.entities.Product;
import com.example.day4.projections.IProCat;
import com.example.day4.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product){
        productRepository.save(product);
        cacheManager.getCache("product").clear();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @Cacheable("product")
    public ResponseEntity list(long cid, int page){
        Sort sort=Sort.by("price").ascending();
        Pageable pageable= PageRequest.of(page, 2);
      //  List<Product> products=productRepository.findAll();
        Page<IProCat> products=productRepository.allProCat(cid, pageable);
        return new ResponseEntity(products, HttpStatus.OK);
    }
    @Scheduled(fixedDelay = 5000, timeUnit = TimeUnit.MILLISECONDS)
    public void call(){
        System.out.println("Call Line");
        cacheManager.getCache("product").clear();

    }


}
