package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @Column
    private String name;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String zipCode;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

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

    public Member(String name, String city, String street, String zipCode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public List<Orders> getOrders() {
        return orders;
    }

}
