package ru.petclinic.servlets.store;

import org.junit.Test;
import ru.petclinic.servlets.models.User;

import static org.junit.Assert.*;

public class JdbcStorageTest {
    @Test
    public void testCreate() throws Exception {
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new User(-1, "test", null));
        final User user = storage.get(id);
        assertEquals(id, user.getId());
        storage.close();
    }
    /*@Test
    public void testDelete() throws Exception {
        final JdbcStorage storage = new JdbcStorage();
        // Exception if user doesn't exist
        final User user = storage.get(1);
        storage.delete(1);
        storage.close();
    }*/
    @Test
    public void testEdit() throws Exception {
        final JdbcStorage storage = new JdbcStorage();
        User testUser = new User(5, "Nastya", null);
        storage.edit(testUser);
        storage.close();
    }
}