package jpqlpractice;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            Member member1 = new Member();
            member1.setUserName("이상우");
            member1.setAge(32);

            Member member2 = new Member();
            member2.setUserName("이유정");
            member2.setAge(30);
            em.persist(member2);

            Team team = new Team();
            team.setTeamName("SBTM");
            member1.setTeam(team);

            em.persist(member1);
            em.persist(member2);
            em.persist(team);

            for (int i = 1; i <= 50; i++) {
                // 로컬 변수는 기본적으로 block scope
                Member member = new Member();
                member.setUserName("Member" + i);
                member.setAge(i);
                em.persist(member);
            }

            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.userName= :userName", Member.class);
            query.setParameter("userName", "이상우");
            // join 문 쓸거면 이렇게 쓰는걸 권장
            TypedQuery<Team> query2 = em.createQuery("SELECT t FROM Member m JOIN m.team t", Team.class);
            List<Member> members = query.getResultList();
            Team myTeam = query2.getSingleResult();

            List<Member> mems = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0).setMaxResults(20).getResultList();

            for (Member m : members) {
                System.out.println("name: " + m.getUserName());
            }
            System.out.println("==================");
            System.out.println("team: " + myTeam.getTeamName());

            System.out.println("============== 페이징 ===================");
            for (Member ms : mems) {
                System.out.println("name : " + ms.getUserName() + ", age : " + ms.getAge());
            }
            // commit, rollback 자체가 DB 트랜잭션임 (tcl Transaction Control Language)
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
