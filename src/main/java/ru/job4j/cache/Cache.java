package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (id, base) -> {
            if (base.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base updateModel = new Base(id, base.getVersion() + 1);
            updateModel.setName(model.getName());
            return updateModel;
        }) != null;

    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base get(Integer key) {
        return memory.get(key);
    }
}
