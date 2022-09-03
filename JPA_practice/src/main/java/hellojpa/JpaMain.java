package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            Member member = new Member();
            member.setName("SANGWOO");

            Member member2 = new Member();
            member2.setName("SANGHA22");

            em.persist(member);
            em.persist(member2);
            em.flush();
            em.clear();

            // JPQL
            List<Member> fMs = em.createQuery("SELECT m FROM Member m where m.name like '%SANG%'", Member.class).getResultList();
            System.out.println("=========JPQL============");
            for (Member m : fMs) {
                System.out.println("name: " + m.getName());
            }
            // Criteria (얘 쓰느니 queryDSL이 낫긴 함)
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);
            // .select, .groupBy, .orderBy 같은 ANSI 기준 쿼리문 전부 지원
            CriteriaQuery<Member> cq = query.select(m).where(cb.like(m.get("name"), "%SANG%"));
            List<Member> resList = em.createQuery(cq).getResultList();

            System.out.println("=========== CRITERIA ==========");

            for (Member mem : resList) {
                System.out.println("name: " + mem.getName());
            }

            List<Member> findMems = em.createNativeQuery("SELECT * FROM MEMBER m WHERE m.name LIKE '%SANG%'", Member.class).getResultList();

            System.out.println("=========== Native Query ============");
            for (Member ms : findMems) {
                System.out.println("name: " + ms.getName());
            }
            // 이때부터 이 객체는 영속 상태가 됨
            //em.persist(member); // 이때 쿼리가 날라가는것은 아님!

            // 이때부터 이 객체 영속 상태가 됨
            //em.persist(member);
            // 이렇게 createQuery로 쿼리 작성 가능 (JPQL)

            // C는 추가할 객체 생성 후 .persist()로 영속성 등록 , find(), .remove() 으로 R, D 가능 업데이트는 알아서 해줌
            // 한 트랜잭션 안에서는 1차 캐쉬를 통해 이미 한번 가져온 값은 쿼리가 나가지 않음!!

//            Parent parent = new Parent();
//
//            Child child1 = new Child();
//            Child child2 = new Child();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);  // 이런거 귀찮을떄 쓰는게 CASCADE (영속성 전이)
//            Member member = new Member();
//            member.setName("멤버1");
//            member.setHomeAddress(new Address("city1", "st1", "10000"));
//
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("피자");
//
//            member.getAdressHistory().add(new Address("old1", "oldsr1", "10000"));
//            member.getAdressHistory().add(new Address("old2", "oldsr2", "9995"));

//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("member: "  + findMember);
//            // 기본적으로 지연 로딩.이렇게 실제로 값을 가져와야 할떄 해당 테이블에 select 쿼리 날림
//            List<Address> addressList = findMember.getAdressHistory();
//            for (Address address : addressList) {
//                System.out.println("address : " + address);
//            }
//
//            findMember.getAdressHistory().remove(new Address("old1", "oldsr1", "10000"));
//            findMember.getAdressHistory().add(new Address("new1", "newstr1", "10001"));

            tx.commit();  // 쿼리는 이 순간에 날라감!
            // .flush() 역할, 1차캐쉬는 사실 특정 쿼리를 find해 오는 시점에 스냅샷을 찍어둠
            // 그래서 flush 가 호출 될떄, 현재 시점과, 초기 시점을 비교해 변경사항을 자동 update 함
            // flush 는 DB와의 동기화 (synchronization)을 위한것! 쿼리 or 커밋할때 자동 호출됨 (모드 설정 가능)

        } catch (Exception e) {
            tx.rollback();
            // Exception stackTrace 출력하는법!
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
