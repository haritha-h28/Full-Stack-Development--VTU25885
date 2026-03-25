package com.example.subscriptionapp.repository;

import com.example.subscriptionapp.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
