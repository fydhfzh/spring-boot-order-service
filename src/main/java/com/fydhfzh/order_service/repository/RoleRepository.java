package com.fydhfzh.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fydhfzh.order_service.entity.Role;
import com.fydhfzh.order_service.entity.User;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.user = :user AND r.deletedAt IS NULL")
    List<Role> findByUserActive(User user);
}
