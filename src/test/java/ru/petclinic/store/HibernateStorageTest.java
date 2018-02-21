package ru.petclinic.store;

import org.junit.Test;
import ru.petclinic.models.User;

import static org.junit.Assert.*;

public class HibernateStorageTest {
    @Test
    public void testCreate() throws Exception {
        final HibernateStorage storage = new HibernateStorage();
        final int id = storage.add(new User(-1, "hibenateTest", null));
        final User user = storage.get(id);
        assertEquals(id, user.getId());
        assertEquals(id, storage.findByLogin("hibenateTest").getId());
        user.setLogin("hibenateEdit");
        storage.edit(user);
        assertEquals(id, storage.findByLogin("hibenateEdit").getId());
        storage.delete(id);
        assertNull(storage.get(id));
        storage.close();
    }

}