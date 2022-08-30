package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Test extends BaseEntity{

    @Column
    private String testNo;
}
