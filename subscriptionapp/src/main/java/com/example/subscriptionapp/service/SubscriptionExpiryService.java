package com.example.subscriptionapp.service;

import com.example.subscriptionapp.entity.Subscription;
import com.example.subscriptionapp.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionExpiryService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionExpiryService.class);

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Run automatically every midnight (Cron: 0 0 0 * * ?)
    @Scheduled(cron = "0 0 0 * * ?")
    public void expireOutdatedSubscriptions() {
        logger.info("Running automated Subscription Expiry Cron Job at Midnight...");
        
        // Retrieve all currently active subscriptions
        List<Subscription> activeSubscriptions = subscriptionRepository.findAll().stream()
                .filter(s -> "ACTIVE".equals(s.getStatus()))
                .toList();
        
        LocalDate today = LocalDate.now();

        int expiredCount = 0;
        for (Subscription sub : activeSubscriptions) {
            try {
                LocalDate endDate = sub.getEndDate(); // Already a LocalDate
                if (today.isAfter(endDate)) {
                    sub.setStatus("EXPIRED");
                    subscriptionRepository.save(sub);
                    expiredCount++;
                    logger.info("Time threshold passed. Expired subscription ID: {} for user: {}", sub.getId(), sub.getUser().getUsername());
                }
            } catch (Exception e) {
                logger.error("Failed to parse date for subscription ID {}: {}", sub.getId(), e.getMessage());
            }
        }
        
        logger.info("Completed automated Expiry Cron Job. Transitioned {} subscriptions to EXPIRED state.", expiredCount);
    }
}
