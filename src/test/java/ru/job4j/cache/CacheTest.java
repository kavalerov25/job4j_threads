package ru.job4j.cache;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CacheTest {

        @Test
        public void add() {
            Cache cache = new Cache();
            cache.add(new Base(1, 1));
            assertThat(cache.get(1).getId(), is(1));
        }

        @Test
        public void whenUpdateTrue() {
            Cache cache = new Cache();
            Base base = new Base(1, 1);
            Base baseTwo = new Base(1, 2);
            cache.add(base);
            baseTwo.setName("newName");
            cache.update(base);
            assertTrue(cache.update(baseTwo));
            assertThat(cache.get(1).getVersion(), is(3));
        }

        @Test
        public void whenUpdateTrueWithOutChanges() {
            Cache cache = new Cache();
            Base base = new Base(1, 1);
            cache.add(base);
            cache.update(base);
            assertTrue(cache.update(base));
            assertTrue(cache.update(base));
            assertThat(cache.get(1).getVersion(), is(4));
        }

        @Test
        public void whenUpdateFalse() {
            Cache cache = new Cache();
            Base base = new Base(1, 1);
            Base baseTwo = new Base(2, 2);
            cache.add(base);
            assertFalse(cache.update(baseTwo));
        }

        @Test(expected = OptimisticException.class)
        public void whenUpdateWithException() {
            Cache cache = new Cache();
            Base base = new Base(1, 1);
            Base baseTwo = new Base(1, 2);
            cache.add(base);
            assertFalse(cache.update(baseTwo));
        }

        @Test
        public void delete() {
            Cache cache = new Cache();
            Base base = new Base(1, 1);
            cache.add(base);
            cache.delete(base);
            assertNull(cache.get(1));
        }
    }
