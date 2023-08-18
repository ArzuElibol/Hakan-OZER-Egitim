package com.example.day4.restController;


import com.example.day4.entities.Product;
import com.example.day4.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;
    final HttpServletRequest req;

    @PostMapping("/save")
    public ResponseEntity save (@RequestBody Product product){
        boolean status=req.getSession().getAttribute("user")==null;
        if (status)
            return new ResponseEntity("Please login", HttpStatus.UNAUTHORIZED);
        return productService.save(product);
    }

    @GetMapping("/list/{cid}/{page}")
    public ResponseEntity list(@PathVariable long cid, @PathVariable int page){
        return  productService.list(cid,page);
    }
}
