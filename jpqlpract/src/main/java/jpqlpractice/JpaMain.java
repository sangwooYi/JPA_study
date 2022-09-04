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

            Team team2 = new Team();
            team2.setTeamName("LG");

            em.persist(member1);
            em.persist(member2);
            em.persist(team);
            em.persist(team2);

            member1.setTeam(team);
            member2.setTeam(team);

            for (int i = 1; i <= 10; i++) {
                // 로컬 변수는 기본적으로 block scope
                Member member = new Member();
                member.setUserName("Member" + i);
                member.setAge(i+5);
                if (i % 2 == 0) {
                    member.setTeam(team);
                } else {
                    member.setTeam(team2);
                }
                em.persist(member);
            }

            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.userName= :userName", Member.class);
            query.setParameter("userName", "이상우");
            // join 문 쓸거면 이렇게 쓰는걸 권장
            TypedQuery<Team> query2 = em.createQuery("SELECT t FROM Member m JOIN m.team t", Team.class);
            List<Member> members = query.getResultList();


            List<Member> mems = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(3).setMaxResults(5).getResultList();

            for (Member m : members) {
                System.out.println("name: " + m.getUserName());
            }


            System.out.println("============== 페이징 ===================");
            for (Member ms : mems) {
                System.out.println("name : " + ms.getUserName() + ", age : " + ms.getAge());
            }
            System.out.println("=============================================");
            // Case 식
            String qs = "SELECT "
                        + "CASE WHEN m.age <= 10 then '학생' "
                        + "     WHEN m.age > 30 then '성인' "
                        + "     ELSE '일반' "
                        + "END "
                        + "FROM Member m";

////            List<String> result = em.createQuery(qs, String.class).getResultList();
////            for (String s : result) {
////                System.out.println("s = " + s);
//            }

            em.flush();
            em.clear();
            // fetch 조인은 조회할 때 연관 엔티티까지 들고오는것 , FetchType.LAZY로 설정하고, 필요할 때 feth join으로 가져오는게 최선임!
            String query1 = "SELECT m FROM Member m JOIN FETCH m.team";
            String query11 = "SELECT t FROM Team t JOIN FETCH t.members";
            // DISTINCT
            String queryD = "SELECT DISTINCT t FROM Team t JOIN FETCH t.members";
            List<Member> fMembers = em.createQuery(query1, Member.class).getResultList();
            List<Team> results = em.createQuery(query11, Team.class).getResultList();
            List<Team> resultsD = em.createQuery(queryD, Team.class).getResultList();
            System.out.println("=========== 페치 조인 =================");
            for (Member m : fMembers) {
                System.out.println("team: " + m.getTeam().getTeamName());
            }
            // 일대다 관계에서 조인할 경우, 테이블에서 데이터가 뻥튀기 된다.
            for (Team t : results) {
                System.out.println("team: " + t.getTeamName() + " || " + "멤버 수: " + t.getMembers().size());
                System.out.println("========= 팀 소속 ============");
                for (Member m : t.getMembers()) {
                    System.out.println("멤버 이름: " + m.getUserName());   // 어쩔수 없이 중복되서 데이터가 출력 됨. 이거 싫으면 DISTINCT 사용 (JPQL에서는 엔티티 중복까지 제거)
                }
            }
            // SQL에서는 아예 동일한 column일때만 제외해주지만, JPQL은 엔티티 중복도 제거해줌
            System.out.println("=========== DISTINCT ===============");
            System.out.println("그냥 사이즈: " + results.size() );  // 12
            System.out.println("DISTINCT : " + resultsD.size());  // 2

            System.out.println("=======================================");
            String q1 = "select m from Member m where m = :member";
            Member fM = em.createQuery(q1, Member.class).setParameter("member", member1).getSingleResult();

            System.out.println("이름 : " + fM.getUserName());
            System.out.println("===================== Named 쿼리 =======================");
            List<Member> members2 = em.createNamedQuery("Member.findByUserName", Member.class)
                    .setParameter("userName", "이유정").getResultList();

            for (Member m : members2) {
                System.out.println("이름: " + m.getUserName());
            }

            System.out.println("=============== 벌크연산 ====================");
            // executeUpdate 는 영향받은 엔티티 수
            int resCount = em.createQuery("update Member m set m.age = m.age + 10").executeUpdate();
            System.out.println(resCount);
            // 반영이 안되어 있음 (벌크연산은 영속성 무시)
            System.out.println(member1.getAge());
            // 벌크연산은 영속성을 무시한다. 따라서 .clear를 해주는 습관을 들여야함
            em.clear();
            Member me = em.createQuery("select m from Member m where m.userName = '이상우'", Member.class).getSingleResult();
            System.out.println(me.getAge());
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
