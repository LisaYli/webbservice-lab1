package dao;

import model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DAO {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    protected static void addUser(String fullName) {
        EntityManager em = emf.createEntityManager();
        User user = new User(fullName);
        System.out.println("*| "+ user.getFullName() + " is saved |*");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();

    }
    protected static String showAllUsers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        em.close();
        return users.toString();
    }


}