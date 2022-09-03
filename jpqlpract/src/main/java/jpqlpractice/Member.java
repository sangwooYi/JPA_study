package jpqlpractice;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Column(length = 30)
    private String userName;
    @Column(length = 10)
    private Integer age;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<OrderInfo> orderInfos = new ArrayList<>();

    public Member() {

    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        if(!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }
}
