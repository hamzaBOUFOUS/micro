package com.hb.security.services;



import com.hb.security.entity.Orders;
import com.hb.security.repository.OrdersRepository;
import com.hb.security.services.OrdersService;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.stream.Stream;

public class StringSequenceIdentifier {

    private OrdersService ordersService;

    public StringSequenceIdentifier(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    public String stringSequenceIdentifier(){
        Orders orders = this.ordersService.findMaxId();
        LocalDate dateNow = LocalDate.now(ZoneId.of("Africa/Casablanca"));
        if(orders == null){
            return "BC"+dateNow.format(DateTimeFormatter.ofPattern("MM-dd"))+""+1;
        }
        String nbrOrder = dateNow.getDayOfMonth() ==1?"1":orders.getReference().split("BC[0-9]{2}-[0-9]{2}")[1];
        return "BC"+dateNow.format(DateTimeFormatter.ofPattern("MM-dd"))+""+(Long.parseLong(nbrOrder)+1);
    }

}