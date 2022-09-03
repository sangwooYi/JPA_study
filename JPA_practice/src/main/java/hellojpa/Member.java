package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ",
//        initialValue = 1, allocationSize = 50)
@Entity
public class Member {

    // PK값 지정해주어야함 @Id 사용
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    private Long id;
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    // 주의, 임베디드 타입에 있는 필드와 같은 필드가 이미 있으면 매핑 exception 발생한다!
    @Embedded
    private Address homeAddress;

    // 값타입 매핑하려면 아래처럼 @ElementCollection, @CollectionTable 어노테이션을 추가해야함.
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    //@Column(name = "FOOD_NAME") // element가 값이 하나이고, 자바에서 제공한 클래스인 경우 이렇게도 가능
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    private List<Address> adressHistory = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩 설정 왠만하면 해둘것
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    // enum 쓰려면 기본값인 .ORDINAL이 아닌 .STRING을 쓰자!
    // (ORDINAL은 실제 운영에서 위험하다. ex USER, ADMIN 에서 GUEST, USER, ADMIN 이렇게 변경시
    // 기존 USER로 저장된 data와 새로 들어온 GUEST 데이터 구분이 DB상으로 안 된다.)
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)   // Date 연,월,일  Time 시,분,초  TIMESTAMP date+time
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;


//    @Lob  // Large Object 가번길이의 큰 데이터를 저장하는데 사용
//    private String description;

    public Member() {

    }

//    public Member(String name, String city, String street, String zipCode) {
//        this.name = name;
//        this.city = city;
//        this.street = street;
//        this.zipCode = zipCode;
//    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public List<Address> getAdressHistory() {
        return adressHistory;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        // 순환참조 방지
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
