package com.example.subscriptionapp.repository;

import com.example.subscriptionapp.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
