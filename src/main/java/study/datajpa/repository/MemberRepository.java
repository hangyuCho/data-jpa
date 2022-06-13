package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    // 파라미터 바인딩은 2가지가 존재
    // ?0 -> 위치기반
    // :names -> 이름기반
    // 위치기반은 파라미터의 위치가 바뀌면 문제가 발생되기 때문에 사용하지 말 것
    // return type의 자세한 내용은 아래 링크를 참조
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.return.types
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    // 1건 취득이라는 전제조건만 성립되면 아래와 같이 유연하게 반환타입을 적용해도 문제 없음
    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 1건
    Optional<Member> findOptionalByUsername(String username);
}
