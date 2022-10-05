package com.hb.security.services;

import com.hb.security.entity.Orders;
import com.hb.security.repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    public Orders saveOrder(Orders orders){
        System.out.println(orders);
        return this.ordersRepository.save(orders);
    }
}
