package com.gymverse.backend.repository;

import com.gymverse.backend.model.Subscription;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionRepository {

    private final DynamoDbTable<Subscription> subscriptionTable;

    public SubscriptionRepository(DynamoDbEnhancedClient enhancedClient) {
        this.subscriptionTable = enhancedClient.table("subscriptions", TableSchema.fromBean(Subscription.class));
    }

    public Subscription save(Subscription subscription) {
        subscriptionTable.putItem(subscription);
        return subscription;
    }

    public Optional<Subscription> findById(String id) {
        return Optional.ofNullable(
            subscriptionTable.getItem(r -> r.key(k -> k.partitionValue(id)))
        );
    }

    public List<Subscription> findAll() {
        List<Subscription> list = new ArrayList<>();
        subscriptionTable.scan().items().forEach(list::add);
        return list;
    }

    public void deleteById(String id) {
        subscriptionTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public List<Subscription> findByMemberId(String memberId) {
        List<Subscription> all = findAll();
        List<Subscription> filtered = new ArrayList<>();
        for (Subscription s : all) {
            if (s.getMemberId().equals(memberId)) {
                filtered.add(s);
            }
        }
        return filtered;
    }
}