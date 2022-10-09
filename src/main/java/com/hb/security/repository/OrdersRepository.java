package com.hb.security.repository;

import com.hb.security.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM orders WHERE id = (SELECT MAX(id) FROM orders)", nativeQuery = true)
    Orders findMaxId();
}
