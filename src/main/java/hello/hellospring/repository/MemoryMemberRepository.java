package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//implements 상속
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence : key 값을 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null값이 반환 될 가능성이 있을 때 Optional 로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store.values()는 멤버값들임
    }

    public void clearStore(){
        store.clear(); //데이터를 비우는 함수
    }
}
