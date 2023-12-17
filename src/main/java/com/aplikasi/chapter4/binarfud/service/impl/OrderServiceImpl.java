package com.aplikasi.chapter4.binarfud.service.impl;

import com.aplikasi.chapter4.binarfud.entity.Merchant;
import com.aplikasi.chapter4.binarfud.entity.Order;
import com.aplikasi.chapter4.binarfud.entity.User;
import com.aplikasi.chapter4.binarfud.repository.OrderRepository;
import com.aplikasi.chapter4.binarfud.repository.UserRepository;
import com.aplikasi.chapter4.binarfud.service.OrderService;
import com.aplikasi.chapter4.binarfud.utils.Config;
import com.aplikasi.chapter4.binarfud.utils.TemplateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateResponse response;

    @Override
    public Map createOrder(Order order) {
        try {
            log.info("create Order");
            return response.sukses(orderRepository.save(order));
        }catch (Exception e){
            log.error("create Order error: "+e.getMessage());
            return response.Error("create Order ="+e.getMessage());
        }
    }

    @Override
    public List<Order> getOrders() {
        log.info("get all Orders");
        return orderRepository.findAll();
    }

    @Override
    public Map getByID(Long order) {
        Optional<Order> getBaseOptional = orderRepository.findById(order);
        if(getBaseOptional.isEmpty()){
            return response.notFound(getBaseOptional);
        }
        return response.templateSukses(getBaseOptional);
    }

    @Override
    public Map getByUserId(Long UserId) {
        try {
            log.info("get order by user");
            if (UserId == null) {
                return response.Error(Config.ID_REQUIRED);
            }
            Optional<User> chekDataDBUser = userRepository.findById(UserId);
            if (chekDataDBUser.isEmpty()) {
                return response.Error(Config.USER_NOT_FOUND);
            }
            chekDataDBUser.get().setId(UserId);
            Optional<Order> getBaseOptional = orderRepository.findByUser(chekDataDBUser.get());
            if(getBaseOptional.isEmpty()){
                return response.notFound(getBaseOptional);
            }
            return response.templateSukses(getBaseOptional);
        }catch (Exception e){
            log.error("get order by user error: "+e.getMessage());
            return response.Error("get order by user ="+e.getMessage());
        }
    }
}
