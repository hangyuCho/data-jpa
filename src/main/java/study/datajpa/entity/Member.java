package study.datajpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    // 왜 프로텍티드?
    // jpa proxy 에서 이용이 가능해짐
    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

}
