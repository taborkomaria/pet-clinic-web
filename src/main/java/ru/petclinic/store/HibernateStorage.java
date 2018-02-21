package ru.petclinic.store;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.petclinic.models.User;

import java.util.Collection;

public class HibernateStorage implements Storage {
    private final SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public HibernateStorage() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public Collection<User> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from User").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int add(final User user) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(user);
            return user.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void edit(User user) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(user);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new User(id, null, null));
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public User get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (User) session.get(User.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public User findByLogin(String login) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            final Query query = session.createQuery("from User as user where user.login=:login");
            query.setString("login", login);
            return (User) query.iterate().next();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        this.factory.close();
    }
}