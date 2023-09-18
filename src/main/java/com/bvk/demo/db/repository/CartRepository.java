package com.bvk.demo.db.repository;

import com.bvk.demo.db.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
}
