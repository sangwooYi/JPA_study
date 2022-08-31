package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        // JPA 가 아래 코드들을 기본적으로 그냥 다 해준다 (영속성 관리)
        // 이 흐름을 이해하자! emf -> em -> tx.begin(), 문제없으면 commit 문제있으면 rollback, -> em.close() -> emf.close()
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 이때부터 이 객체 영속 상태가 됨
            //em.persist(member);
            // 이렇게 createQuery로 쿼리 작성 가능 (JPQL)

            // C는 추가할 객체 생성 후 .persist()로 영속성 등록 , find(), .remove() 으로 R, D 가능 업데이트는 알아서 해줌
            // 한 트랜잭션 안에서는 1차 캐쉬를 통해 이미 한번 가져온 값은 쿼리가 나가지 않음!!


//            Album album = new Album();  // 기본적으로 null 허용이다.
//            album.setArtist("하하");
//            album.setName("김치");
//
//            em.persist(album);
//
//            em.flush();
//            em.clear();
//
//            Album alb = em.getReference(Album.class, album.getId()); // 이때는 프록시로만 가져옴! (select 쿼리가 나가지 않음)
//            System.out.println("who is this? : " + alb.getClass()); // 프록시(가짜 엔티티)를 가져온것!
//
//            em.detach(alb);  // 이렇게 준영속 상태에서 프록시 초기화 하려고 하면 LazyInitializationException 발생
//
//            alb.getArtist();

            Parent parent = new Parent();

            Child child1 = new Child();
            Child child2 = new Child();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);  // 이런거 귀찮을떄 쓰는게 CASCADE (영속성 전이)

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
