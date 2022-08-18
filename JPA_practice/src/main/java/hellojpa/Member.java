package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 50)
public class Member {

    // PK값 지정해주어야함 @Id 사용
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")
    private String userName;

    private Integer age;

    // enum 쓰려면 기본값인 .ORDINAL이 아닌 .STRING을 쓰자!
    // (ORDINAL은 실제 운영에서 위험하다. ex USER, ADMIN 에서 GUEST, USER, ADMIN 이렇게 변경시
    // 기존 USER로 저장된 data와 새로 들어온 GUEST 데이터 구분이 DB상으로 안 된다.)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)   // Date 연,월,일  Time 시,분,초  TIMESTAMP date+time
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;


    @Lob  // Large Object 가번길이의 큰 데이터를 저장하는데 사용
    private String description;

    public Member() {

    }

    public Member(Long id, String userName, Integer age, RoleType roleType, Date createdDate, Date lastModifiedDate, String description) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.roleType = roleType;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
