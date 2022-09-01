package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member {

    // PK값 지정해주어야함 @Id 사용
    @Id
    @GeneratedValue // AUTO가 default값
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 20)
    private String memberName;

    // 멤버 입장이서 팀과 N:1 관계.
    // Member.team 이 연관관계의 주인, 즉 외래키가 있는 테이블이 주인 테이블!
    // N:1 에서는 N쪽이 주인 테이블임(N 입장에선 1 확정, 반대는 확정이 안되므로!)
    @ManyToOne
    // FK 값은 참조하는 테이블의 PK 값이어야 한다!
    // 우선 단방향 mapping!
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    @OneToOne
    // 외래키 관리하는 주인 테이블에 JoinColumn를 걸어주어야만 한다.
    // 이거 안걸어주면 기본적으로 조인테이블 사용함
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;


    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public Team getTeam() {
        return team;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // 연관관게 편의 메서드
    public void setTeam(Team team) {
        this.team = team;
        // 이래야 실수 방지! + 무한 참조 방지 (이거와 addMember 중 하나만 작성해주면 된다)
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);  //없을때만 추가
        }
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }
    public Member() {

    }
    public Member(String memberName, Team team) {
        this.memberName = memberName;
        this.team = team;
    }
}
