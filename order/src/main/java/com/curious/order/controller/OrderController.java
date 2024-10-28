package com.curious.order.controller;

import com.curious.order.records.OrderRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/orders")
public class OrderController {

    private final KafkaTemplate<String,OrderRequest> kafkaTemplate;

    public OrderController(KafkaTemplate<String,OrderRequest> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderRequest orderRequest){
        kafkaTemplate.send("orders",orderRequest);
    }

}


