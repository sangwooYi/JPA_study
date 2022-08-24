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
            Team team = new Team();
            team.setTeamName("TeamC");
            em.persist(team);

            Member member = new Member("memberA", team);
            em.persist(member);

            // 이 예제에서는 flush(), clear()를 통해 DB sync 맞춰 주고, 영속성 제거 해주어야
            // 제대로 members에 대한 값을 가져온다! (주의!)
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m: " + m.getMemberName());
            }
//
//            System.out.println("member: " + member);
//            Team team = member.getTeam();
//            System.out.println("TEAM: " + team);

            tx.commit();  // 쿼리는 이 순간에 날라감!

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
