package ru.vsu.dao.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T, K> {

    Optional<T> findById(K id);

    T save(T entity);

    void deleteById(Integer id);

    Collection<T> findAll();


}
