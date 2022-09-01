package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Test extends BaseEntity{

    @Column
    private String testNo;

    // 임베디드 타입 , Period의 값들이 알아서 여기에 매칭 된다.
    @Embedded  // 사용하는 곳에 embedded 사용
    private Period period;
}
