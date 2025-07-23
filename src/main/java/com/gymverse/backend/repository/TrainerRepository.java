package com.gymverse.backend.repository;

import com.gymverse.backend.model.Trainer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainerRepository {
    private final DynamoDbTable<Trainer> trainerTable;

    public TrainerRepository(DynamoDbEnhancedClient enhancedClient) {
        this.trainerTable = enhancedClient.table("trainers", TableSchema.fromBean(Trainer.class));
    }

    public Trainer save(Trainer trainer) {
        trainerTable.putItem(trainer);
        return trainer;
    }

    public Trainer getById(String trainerId) {
        return trainerTable.getItem(r -> r.key(k -> k.partitionValue(trainerId)));
    }

    public List<Trainer> getAll() {
        List<Trainer> trainers = new ArrayList<>();
        trainerTable.scan().items().forEach(trainers::add);
        return trainers;
    }

    public void delete(String trainerId) {
        trainerTable.deleteItem(r -> r.key(k -> k.partitionValue(trainerId)));
    }
}
