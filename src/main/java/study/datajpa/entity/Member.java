package study.datajpa.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
// JPA NamedQuery의 특징
// Entity에 정의해서 사용
// 장점 : 컴파일 시 쿼리의 문제가 있는지 한번 검증과정을 거침
// 단점 : Spring Data JPA에서 제공하는 기능에 비해서 기재할 내용이 많음
//       (실무에서는 거의 사용할 일이 없을 것임.)
// 주의 : em.createQuery로 작성된 쿼리는 컴파일 시 에러가 발생되지 않음..
//       그러니 가능하다면 쓰지 않도록 할 것
//@NamedQuery(
//        name="Member.findByUsername",
//        query="select m from Member m where m.username = :username"
//)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    // 모든 연관관계는 기본적으로 지연(Lazy)로 설정해야함
    // 실무에서 성능 관련으로 Lazy가 아니라면 원하는 성능을 뽑아내는데
    // 큰 어려움이 있음..

    // 지연 로딩이란?
    // 멤버의 값만 취득하고 연결된 Team은 실제로 값을 확인할 때 취득하는 기법
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }


    public void changeTeam(Team team) {
        this.team = team;
        team.getMember().add(this);
    }

}
