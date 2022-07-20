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
    public void whenUpdateThanTrue() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        assertTrue(cache.update(model));
        assertThat(cache.get(model.getId()).getVersion(), is(1));
    }

        @Test
        public void whenUpdateThanTru() {
            Cache cache = new Cache();
            Base base = new Base(1, 1);
            cache.add(base);
            assertTrue(cache.update(base));
            assertThat(cache.get(1).getVersion(), is(2));
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
