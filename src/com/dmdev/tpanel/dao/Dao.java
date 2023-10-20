package com.dmdev.tpanel.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    boolean delete(K id);
    void update(T user);

    T save(T user);

}
