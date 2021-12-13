package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String SQL =
                    "CREATE TABLE IF NOT EXISTS users\n" +
                            "(\n" +
                            "id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                            "name VARCHAR(50),\n" +
                            "lastName VARCHAR(50),\n" +
                            "age TINYINT CHECK (age > 0)\n" +
                            ");\n";
            Query query = session.createSQLQuery(SQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String SQL = "DROP TABLE IF EXISTS users;";
            Query query = session.createSQLQuery(SQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String SQL = "INSERT users(name, lastName, age)" +
                    "VALUES ('" +
                    name + "', '" +
                    lastName + "', " +
                    age + ");";
            Query query = session.createSQLQuery(SQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String SQL = "DELETE FROM users\n" +
                    "WHERE id=" + id;
            Query query = session.createSQLQuery(SQL).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users;").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
