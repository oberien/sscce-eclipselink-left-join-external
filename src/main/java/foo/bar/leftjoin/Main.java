package foo.bar.leftjoin;

import foo.bar.leftjoin.entities.Other;
import foo.bar.leftjoin.entities.SubClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("leftjoin");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        SubClass t = new SubClass("Sub1");
        em.persist(t);
        t = new SubClass("Sub2");
        em.persist(t);

        Other e = new Other("Ext1");
        em.persist(e);

        em.getTransaction().commit();

        // `JOIN ON` and `LEFT OUTER JOIN ON` of Other with SubClass results in
        //   org.eclipse.persistence.exceptions.QueryException
        //     Exception Description: The field [SUPERCLASS.DTYPE] in this expression has an invalid table in this context.

        List<?> result = em.createQuery("SELECT ext, sub from Other ext JOIN SubClass sub ON sub.name = 'Sub1'").getResultList();
        System.out.println("Result length: " + result.size());
        result = em.createQuery("SELECT ext, sub from Other ext LEFT OUTER JOIN SubClass sub ON sub.name = 'Sub1'").getResultList();
        System.out.println("Result length: " + result.size());
    }
}
