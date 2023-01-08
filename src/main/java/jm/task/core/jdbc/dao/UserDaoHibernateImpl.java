package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery("CREATE TABLE IF NOT EXISTS `mydb`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(60) NULL,\n" +
                    "  `lastName` VARCHAR(60) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));\n", User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery("DROP TABLE IF EXISTS users", User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery("DELETE User WHERE id = :name").setParameter("name", id);
            query.executeUpdate();
            transaction.commit();
        }
    }


    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery("delete from users", User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
