package com.gymverse.backend.repository;

import org.springframework.stereotype.Repository;

import com.gymverse.backend.model.Gym;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


@Repository
public class GymRepository {

    private final DynamoDbEnhancedClient enhancedClient;
    private DynamoDbTable<Gym> gymTable;

    public GymRepository(DynamoDbClient dynamoDbClient) {
        this.enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @PostConstruct
    public void init() {
        this.gymTable = enhancedClient.table("gym", TableSchema.fromBean(Gym.class));
    }

    public void save(Gym gym) {
        gymTable.putItem(gym);
    }

    public Gym getById(String gymId) {
        return gymTable.getItem(r -> r.key(k -> k.partitionValue(gymId)));
    }

    // Add more methods as needed
}