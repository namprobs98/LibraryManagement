package com.librarymanagement.repository;

import com.librarymanagement.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryMemberRepository implements MemberRepository {
    private final Map<String, Member> data = new LinkedHashMap<>();

    @Override
    public void save(Member member) {
        data.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteById(String id) {
        data.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return data.containsKey(id);
    }

    @Override
    public void replaceAll(List<Member> members) {
        data.clear();
        for (Member member : members) {
            data.put(member.getId(), member);
        }
    }
}
