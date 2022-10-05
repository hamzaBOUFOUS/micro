package com.hb.security.dto.mapper;

import com.hb.security.dto.OrderDTO;
import com.hb.security.entity.Orders;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Orders toOrders(OrderDTO orderDTO);
    OrderDTO toOrderDTO(Orders orders);
}
