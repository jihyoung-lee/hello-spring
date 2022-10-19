package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach // 메서드 단위별로 끝날때마다 불러오는 콜백함수
    public void afterEach(){
        memberRepository.clearStore(); //테스트는 독립적으로(서로 의존관계가 없게끔) 매번 데이터를 비워주도록함
    }
    @Test
    void join() {
        //given 기반으로 할 데이터
        Member member = new Member();
        member.setName("test");

        //when 검증 케이스
        Long saveId = memberService.join(member);
        //then 검증부분
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("test1");
        Member member2 = new Member();
        member2.setName("test1");
        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}