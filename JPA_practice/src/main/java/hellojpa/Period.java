package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable // 정의하는 곳에 Embeddable 사용
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
