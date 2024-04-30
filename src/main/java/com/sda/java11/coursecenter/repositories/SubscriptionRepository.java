package com.sda.java11.coursecenter.repositories;

import com.sda.java11.coursecenter.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
