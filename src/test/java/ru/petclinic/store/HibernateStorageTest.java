package ru.petclinic.store;

import org.junit.Ignore;
import org.junit.Test;
import ru.petclinic.models.Message;
import ru.petclinic.models.Role;
import ru.petclinic.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class HibernateStorageTest {

    @Ignore
    @Test
    public void testCreate() throws Exception {
        final HibernateStorage storage = new HibernateStorage();
        //create and add new user
        final int id = storage.add(new User(-1, "hibenateTest", "hibenateTest@test"));
        //get user by id
        final User user = storage.get(id);
        assertEquals(id, user.getId());
        assertEquals(id, storage.findByLogin("hibenateTest").getId());
        //edit login
        user.setLogin("hibenateEdit");
        //update this user
        storage.edit(user);
        assertEquals(id, storage.findByLogin("hibenateEdit").getId());
        //delete user by id
        storage.delete(id);
        assertNull(storage.get(id));
        storage.close();
    }
    @Test
    public void testCreateUser() throws Exception {
        final HibernateStorage storage = new HibernateStorage();

        //create user
        User user = new User();
        user.setLogin("hibenateTest");
        user.setEmail("hibenateTest@test");

        //add role to user
        Role role = new Role();
        role.setName("hibenateRole");
        user.setRole(role);

        //add user
        final int id = storage.add(user);
        user = storage.get(id);

        //create message
        Message message = new Message();
        message.setUser(user);
        message.setText("text");
        HashSet<Message> messages = new HashSet<>();

        //add messages to user
        messages.add(message);
        user.setMessages(messages);
        storage.edit(user);


        assertEquals(1, storage.get(id).getMessages().size());
        storage.delete(id);
        storage.close();
    }
}