package com.netheve.andikagunshop.repository;

import com.netheve.andikagunshop.entity.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {
}
