package com.hb.security.services;

import com.hb.security.entity.Orders;
import com.hb.security.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    public Orders saveOrder(Orders orders){
        return this.ordersRepository.save(orders);
    }

    public List<Orders> allOrder(){
        return this.ordersRepository.findAll();
    }
}
