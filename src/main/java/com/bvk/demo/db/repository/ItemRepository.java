package com.bvk.demo.db.repository;


import com.bvk.demo.db.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    List<ItemEntity> findByNameContaining(String name);
}
