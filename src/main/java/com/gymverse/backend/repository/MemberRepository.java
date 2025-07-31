package com.gymverse.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gymverse.backend.model.Member;

import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class MemberRepository {

    private final DynamoDbTable<Member> memberTable;

    public MemberRepository(DynamoDbEnhancedClient enhancedClient) {
        this.memberTable = enhancedClient.table("members", TableSchema.fromBean(Member.class));
    }

    public Member save(Member member) {
        memberTable.putItem(member);
        return member;
    }

    public Member getById(String tenantId, String memberId) {
        Key key = Key.builder()
                .partitionValue(tenantId)
                .sortValue(memberId)
                .build();
        return memberTable.getItem(r -> r.key(key));
    }

    public List<Member> findAllByTenantId(String tenantId) {
        List<Member> members = new ArrayList<>();
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder()
                .partitionValue(tenantId)
                .build());

        memberTable.query(r -> r.queryConditional(queryConditional)).items().forEach(members::add);
        return members;
    }

    public void delete(String tenantId, String memberId) {
        Key key = Key.builder()
                .partitionValue(tenantId)
                .sortValue(memberId)
                .build();
        memberTable.deleteItem(r -> r.key(key));
    }
}