package com.aplikasi.chapter4.binarfud.service;

import com.aplikasi.chapter4.binarfud.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map createOrder(Order order);
    List<Order> getOrders();
    Map getByID(Long order);
    Map getByUserId(Long UserId);
}
