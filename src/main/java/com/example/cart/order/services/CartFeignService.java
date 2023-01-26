package com.example.cart.order.services;

import com.example.cart.order.entities.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "cartFeign", url = "http://10.20.5.8:8090/stock")
public interface CartFeignService {

    @PostMapping("/reduceStock/{skuId}/{quantity}")
    Stock reduceStock(@PathVariable("skuId") String skuId, @PathVariable("quantity") String quantity);
}
