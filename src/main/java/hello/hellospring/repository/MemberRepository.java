package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // save하면 저장소에 바로 저장되고 저장한 Member 객체를 반환
    Optional<Member> findById(Long id); // optional은 없으면 null이 반환될 때의 일을 처리
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
