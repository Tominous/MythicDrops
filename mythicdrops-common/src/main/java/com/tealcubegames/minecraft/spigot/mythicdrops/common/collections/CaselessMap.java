package com.tealcubegames.minecraft.spigot.mythicdrops.common.collections;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

public final class CaselessMap<V> extends HashMap<String, V> {

    public CaselessMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public CaselessMap(int initialCapacity) {
        super(initialCapacity);
    }

    public CaselessMap() {
    }

    public CaselessMap(Map<? extends String, ? extends V> m) {
        Preconditions.checkNotNull(m);
        for (Map.Entry<? extends String, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V get(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("key must be an instance of String");
        }
        return super.get(((String) key).toLowerCase());
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("key must be an instance of String");
        }
        return super.containsKey(((String) key).toLowerCase());
    }

    @Override
    public V put(String key, V value) {
        return super.put(key.toLowerCase(), value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        for (Map.Entry<? extends String, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("key must be an instance of String");
        }
        return super.remove(((String) key).toLowerCase());
    }

}
