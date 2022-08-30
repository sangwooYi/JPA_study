package hellojpa;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;


    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
