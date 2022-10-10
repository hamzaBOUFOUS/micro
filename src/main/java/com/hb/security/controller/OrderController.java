package com.hb.security.controller;

import com.hb.security.dto.OrderDTO;
import com.hb.security.dto.mapper.OrderMapper;
import com.hb.security.entity.OrderItem;
import com.hb.security.entity.Orders;
import com.hb.security.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return this.ordersService.saveOrder(orders);
    }


    @GetMapping("/all")
    public List<Orders> allOrder(){
        return this.ordersService.allOrder();
    }
}
