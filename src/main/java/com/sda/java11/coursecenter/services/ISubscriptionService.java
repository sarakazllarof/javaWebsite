package com.sda.java11.coursecenter.services;

import com.sda.java11.coursecenter.dto.subscribe.SubscriptionWrite;
import com.sda.java11.coursecenter.entities.Subscription;

import java.util.List;

public interface ISubscriptionService {
    Subscription save(SubscriptionWrite dto);
    List<Subscription> getAllSubscription();

    String delete(SubscriptionWrite dto);
}
