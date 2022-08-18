package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    // PK값 지정해주어야함 @Id 사용
    @Id
    @GeneratedValue // AUTO가 default값
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 20)
    private String memberName;

    // 멤버 입장이서 팀과 N:1 관계
    @ManyToOne
    // FK 값은 참조하는 테이블의 PK 값이어야 한다!
    // 우선 단방향 mapping!
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public Team getTeam() {
        return team;
    }

    public Member() {

    }
    public Member(String memberName, Team team) {
        this.memberName = memberName;
        this.team = team;
    }
}
