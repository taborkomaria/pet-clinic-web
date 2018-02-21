package ru.petclinic.store;

import org.junit.Test;
import ru.petclinic.models.User;

import static org.junit.Assert.*;

public class JdbcStorageTest {
    @Test
    public void testCreate() throws Exception {
        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(new User(-1, "JdbcTest", null));
        final User user = storage.get(id);
        assertEquals(id, user.getId());
        assertEquals(id, storage.findByLogin("JdbcTest").getId());
        User testUser = new User(id, "JdbcTestEdit", null);
        storage.edit(testUser);
        assertEquals(id, storage.findByLogin("JdbcTestEdit").getId());
        storage.delete(id);
        assertNull(storage.get(id));
        storage.close();
    }
}