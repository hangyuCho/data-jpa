package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    // 외래키가 없는 곳에 mappedBy를 설정하는 것을 권장함.
    @OneToMany(mappedBy = "team")
    private List<Member> member = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
