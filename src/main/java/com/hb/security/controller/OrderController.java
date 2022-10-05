package com.hb.security.controller;

import com.hb.security.dto.OrderDTO;
import com.hb.security.dto.mapper.OrderMapper;
import com.hb.security.entity.Orders;
import com.hb.security.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrdersService ordersService;
    private OrderMapper orderMapper;
    @Autowired
    public OrderController(OrdersService ordersService, OrderMapper orderMapper){
        this.ordersService = ordersService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/add")
    public Orders saveOrder(@RequestBody Orders orders){
        System.out.println(orders);
        return this.ordersService.saveOrder(orders);
    }
}