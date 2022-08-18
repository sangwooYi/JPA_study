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
//            Team team = new Team("teamB");
//            em.persist(team);
//
//            Member member = new Member("memberA", team);
//            em.persist(member);

            Member member = em.find(Member.class, 3L);

            System.out.println("member: " + member);
            Team team = member.getTeam();
            System.out.println("TEAM: " + team);

            tx.commit();  // 쿼리는 이 순간에 날라감!

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
