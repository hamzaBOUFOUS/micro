package com.hb.security.dto.mapper;

import com.hb.security.dto.OrderDTO;
import com.hb.security.entity.Orders;
import com.hb.security.utils.Statu;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "statu", ignore = true)
    @Mapping(target = "dateCreatedOrder", ignore = true)
    @Mapping(target = "reference", ignore = true)
    @Mapping(target = "active", ignore = true)
    Orders toOrders(OrderDTO orderDTO);

    OrderDTO toOrderDTO(Orders orders);
}
