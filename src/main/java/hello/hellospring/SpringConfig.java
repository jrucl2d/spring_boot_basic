package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    
//    private DataSource datasource; // 데이터 소스를 가져옴
//
//    public SpringConfig(DataSource datasource) {
//        this.datasource = datasource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // interface만 만들어놔도 알아서 구현체를 만들기 때문에 바로 DI해주면 된다.
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    // 직접 스프링 빈을 설정
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(datasource); // 여기를 Jdbc로만 연결해주면 됨
////        return new JdbcTemplateMemberRepository(datasource); // JdbcTemplate 쓰면
//        return new JpaMemberRepository(em); // JPA 사용하면
//    }
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository()); // 스프링 데이터 JPA에서는 필요 없다.
//    }
}
