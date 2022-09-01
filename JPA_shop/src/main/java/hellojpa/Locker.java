package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "locker")
    // 주인 테이블의 외래키 값과 (Member 테이블에서 locker 테이블의 PK값을 FK(외래키)로 갖는것) 매핑시켜주는 것
    private Member member;
}
