package com.gymverse.backend.repository;

import com.gymverse.backend.model.Trainer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

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

    public Trainer getById(String tenantId, String trainerId) {
        return trainerTable.getItem(r -> r.key(k -> 
            k.partitionValue(tenantId).sortValue(trainerId)
        ));
    }

    public List<Trainer> getAllTrainers(String tenantId) {
        List<Trainer> trainers = new ArrayList<>();
        trainerTable.query(r -> r.queryConditional(
            QueryConditional.keyEqualTo(k -> k.partitionValue(tenantId)))
        ).items().forEach(trainers::add);
        return trainers;
    }

    public void delete(String tenantId, String trainerId) {
        trainerTable.deleteItem(r -> r.key(k -> 
            k.partitionValue(tenantId).sortValue(trainerId)
        ));
    }
}