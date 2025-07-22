package com.gymverse.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gymverse.backend.model.Member;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class MemberRepository {

    private final DynamoDbTable<Member> memberTable;

    public MemberRepository(DynamoDbEnhancedClient enhancedClient) {
        this.memberTable = enhancedClient.table("members", TableSchema.fromBean(Member.class));
    }

    public void save(Member member) {
        memberTable.putItem(member);
    }

    public Member getById(String memberId) {
        return memberTable.getItem(r -> r.key(k -> k.partitionValue(memberId)));
    }

    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        memberTable.scan().items().forEach(members::add);
        return members;
    }

    public void delete(String memberId) {
        memberTable.deleteItem(r -> r.key(k -> k.partitionValue(memberId)));
    }
}