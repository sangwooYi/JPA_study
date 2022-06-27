package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // persistence.xml에서 persistence-unit 태그에서 name 속성의 값으로 설정한 값을 적어주어야 함
        // 1. EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 2. EntityManager 생성
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        hellojpa.Member member = new hellojpa.Member();
        member.setId(1L);

        String data = "helloA";
        member.setName(data);
        // 영속성 적용
        em.persist(member);

        tx.commit();
        em.close();
        emf.close();
    }
}
