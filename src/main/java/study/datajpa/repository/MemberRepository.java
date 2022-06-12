package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    // 쿼리 어노테이션의 장점 : 쿼리가 틀리면 오류가 발생됨(이름이 없는 namedQuery)
    // 복잡한 정적쿼리는 이런방식으로 사용
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 유저의 이름만 취득할 때
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // entity로 취득될 데이터를 DTO로 취득하기
    // jpql에서 지원하는 new operation
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
