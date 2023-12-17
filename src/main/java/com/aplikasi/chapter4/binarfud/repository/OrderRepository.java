package com.aplikasi.chapter4.binarfud.repository;
import com.aplikasi.chapter4.binarfud.entity.Merchant;
import com.aplikasi.chapter4.binarfud.entity.Order;
import com.aplikasi.chapter4.binarfud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> , JpaSpecificationExecutor<Order> {
    Optional<Order> findByUser(User user);
    //
}
