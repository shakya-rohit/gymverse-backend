package com.gymverse.backend.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gymverse.backend.model.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class UserRepository {

    private final DynamoDbTable<User> userTable;

    public UserRepository(DynamoDbEnhancedClient enhancedClient) {
        this.userTable = enhancedClient.table("users", TableSchema.fromBean(User.class));
    }

    public Optional<User> findByUsername(String username) {
        User user = userTable.getItem(r -> r.key(k -> k.partitionValue(username)));
        return Optional.ofNullable(user);
    }

    public User save(User user) {
        userTable.putItem(user);
        return user;
    }
}