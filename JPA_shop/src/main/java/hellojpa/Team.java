package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    @Column(length = 20)
    private String teamName;

    // 내가 참조하는 상대 Many 테이블에서의 Team 테이블을 참조하는 값
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }
    public List<Member> getMembers() {
        return members;
    }


    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void addMembers(Member member) {
        this.members.add(member);
        // 무한 참조 방지
        if(member.getTeam() != this) {
            member.setTeam(this);
        }
    }
    public Team() {

    }
}
