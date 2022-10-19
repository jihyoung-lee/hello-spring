package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    /*
    * 클래스나 변수에 final을 붙이면 처음 정의된 상태가 변하지 않는 것을 보장한다는 의미이다.
    * Java에서 변수들은 기본적으로 가변적인데, 변수에 final 키워드를 붙여 참조값을 변경 못하도록 해 불변성을 확보할 수 있다.
    */

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     */
    public Long join(Member member){
        //중복 name 멤버 안되는 경우
        validateDuplicateMember(member); //같은 회원 중복 검사
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
