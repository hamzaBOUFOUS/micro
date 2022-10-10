package com.hb.security.services;

import com.hb.security.entity.OrderItem;
import com.hb.security.entity.Orders;
import com.hb.security.repository.OrdersRepository;
import com.hb.security.utils.Statu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;
    private OrderItemService orderItemService;
    private StringSequenceIdentifier stringSequenceIdentifier;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, OrderItemService orderItemService){
        this.ordersRepository = ordersRepository;
        this.orderItemService = orderItemService;
        this.stringSequenceIdentifier = new StringSequenceIdentifier(this);
    }

    public Orders saveOrder(Orders orders){
        orders.setDateCreatedOrder(LocalDateTime.now(ZoneId.of("Africa/Casablanca")));
        orders.setActive(true);
        orders.setStatu(Statu.PROCESSING);
        orders.setReference(this.stringSequenceIdentifier.stringSequenceIdentifier());
        Orders res = this.ordersRepository.save(orders);
        for(OrderItem orderItem:res.getItems()){
            orderItem.setOrders(res);
            this.orderItemService.saveOrderItem(orderItem);
        }
        return res;
    }

    public List<Orders> allOrder(){
        return this.ordersRepository.findAll();
    }

    public Orders findMaxId(){
        return this.ordersRepository.findMaxId();
    }
}
