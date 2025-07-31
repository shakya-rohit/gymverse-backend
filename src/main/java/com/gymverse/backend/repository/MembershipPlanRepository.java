package com.gymverse.backend.repository;

import com.gymverse.backend.model.MembershipPlan;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipPlanRepository {

    private final DynamoDbTable<MembershipPlan> membershipPlanTable;

    public MembershipPlanRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.membershipPlanTable = enhancedClient.table("membership-plans", TableSchema.fromBean(MembershipPlan.class));
    }

    public MembershipPlan save(MembershipPlan plan) {
        membershipPlanTable.putItem(plan);
        return plan;
    }

    public Optional<MembershipPlan> findById(String tenantId, String planId) {
        MembershipPlan plan = membershipPlanTable.getItem(r -> r.key(k -> 
                k.partitionValue(tenantId).sortValue(planId)));
        return Optional.ofNullable(plan);
    }

    public List<MembershipPlan> findAllByTenantId(String tenantId) {
        List<MembershipPlan> plans = new ArrayList<>();
        membershipPlanTable.query(r -> r.queryConditional(
                QueryConditional.keyEqualTo(k -> k.partitionValue(tenantId))
        )).items().forEach(plans::add);
        return plans;
    }

    public void deleteById(String tenantId, String planId) {
        membershipPlanTable.deleteItem(r -> r.key(k ->
                k.partitionValue(tenantId).sortValue(planId)));
    }
}