package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // JPA 가 아래 코드들을 기본적으로 그냥 다 해준다 (영속성 관리)
        // 이 흐름을 이해하자! emf -> em -> tx.begin(), 문제없으면 commit 문제있으면 rollback, -> em.close() -> emf.close()
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속 상태
//            Member member = new Member();
//            member.setUserName("sang11woo");
//            member.setAge(19);
//            member.setRoleType(RoleType.USER);
            // 이때부터 이 객체는 영속 상태가 됨
            //em.persist(member); // 이때 쿼리가 날라가는것은 아님!
            // 이렇게 createQuery로 쿼리 작성 가능 (JPQL)
            // 클래스.class 는 인스턴스.getClass() 와 동일한 역할임 (클래스에 직접 접근하는법, 인스턴스가 없을때 보통 사용)
//            List<Member> members = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
//            for (Member member : members) {
//                System.out.println("member: " + member.getName());
//            }
            // C는 추가할 객체 생성 후 .persist()로 영속성 등록 , find(), .remove() 으로 R, D 가능 업데이트는 알아서 해줌
            // 한 트랜잭션 안에서는 1차 캐쉬를 통해 이미 한번 가져온 값은 쿼리가 나가지 않음!!
//            Member member1 = em.find(Member.class, 1L);
//            Member member2 = em.find(Member.class, 1L);
//            // true 동일성 보장.
//            System.out.println(member2 == member1);

            tx.commit();  // 쿼리는 이 순간에 날라감!
            // .flush() 역할, 1차캐쉬는 사실 특정 쿼리를 find해 오는 시점에 스냅샷을 찍어둠
            // 그래서 flush 가 호출 될떄, 현재 시점과, 초기 시점을 비교해 변경사항을 자동 update 함
            // flush 는 DB와의 동기화 (synchronization)을 위한것! 쿼리 or 커밋할때 자동 호출됨 (모드 설정 가능)

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
