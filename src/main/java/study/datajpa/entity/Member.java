package study.datajpa.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
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
