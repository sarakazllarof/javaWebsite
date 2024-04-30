package com.sda.java11.coursecenter.services;

import com.sda.java11.coursecenter.dto.courses.CourseWrite;
import com.sda.java11.coursecenter.dto.subscribe.SubscriptionWrite;
import com.sda.java11.coursecenter.entities.Course;
import com.sda.java11.coursecenter.entities.Subscription;
import com.sda.java11.coursecenter.repositories.SubscriptionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService{
    @NonNull
    protected SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(SubscriptionWrite dto) {
        Subscription subscription = new Subscription();
        subscription.setFirstName(dto.getFirstName());
        subscription.setLastName(dto.getLastName());
        subscription.setCardNumber(dto.getCardNumber());
        subscription.setEmail(dto.getEmail());
        subscription.setCourseId(dto.getCourseId());

        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    @Override
    public String delete(SubscriptionWrite dto) {
        if (dto == null || dto.getId() == null)
            return "Provide a valid course id";

        Optional<Subscription> dbCourse = subscriptionRepository.findById(dto.getId());
        return dbCourse.map(o ->  {
            subscriptionRepository.delete(o);
            return "Subscription deleted successfully!";
        }).orElse("Subscription not present in database!");
    }
}
