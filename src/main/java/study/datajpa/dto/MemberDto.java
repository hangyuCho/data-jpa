package study.datajpa.dto;

import lombok.Data;

// entity에는 Data를 사용하지 말것 entity에서는 set을 사용하지 않는 것이 좋음
@Data
public class MemberDto {

    private Long id;
    private String username;
    private String testName;

    public MemberDto(Long id, String username, String testName) {
        this.id = id;
        this.username = username;
        this.testName = testName;
    }
}
