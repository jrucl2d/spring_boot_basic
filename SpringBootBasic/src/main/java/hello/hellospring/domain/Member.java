package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa 사용
public class Member {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시스템이 정할 아이디

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
