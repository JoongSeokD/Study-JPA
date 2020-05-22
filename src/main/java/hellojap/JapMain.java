package hellojap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JapMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //비영속
            Member member = new Member();
            member.setId(101L) ;
            member.setName("HelloJAP2");

            //영속 DB 저장 X
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            // 1차 캐시 DB에서 Select하지 않는다.
            Member member1 = em.find(Member.class, 101L);
            System.out.println(member1.getId());
            System.out.println(member1.getName());



            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();

    }
}
