package com.demo.eda_order_service.controller;

import com.demo.eda_base_domain.model.Order;
import com.demo.eda_base_domain.model.OrderEvent;
import com.demo.eda_order_service.kafka.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        //create order-event
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order created");
        orderEvent.setStatus("CREATED");
        orderEvent.setOrder(order);

        //produce message
        orderProducer.produceOrderEvent(orderEvent);

        return ResponseEntity.ok("Order created");
    }

}
