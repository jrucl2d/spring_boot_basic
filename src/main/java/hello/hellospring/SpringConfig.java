package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    
    private DataSource datasource; // 데이터 소스를 가져옴

    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    // 직접 스프링 빈을 설정
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(datasource); // 여기를 Jdbc로만 연결해주면 됨
        return new JdbcTemplateMemberRepository(datasource);
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
}
