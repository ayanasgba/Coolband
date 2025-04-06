package kg.geeks.coolband.services;

import java.util.List;

public interface BaseService<Z, T>{
    List<Z> getAll();

    Z save(T t);

    Z getById(Long id);

    Z deleteById(Long id);

    Z patch(Long id, T t) throws IllegalAccessException, NoSuchFieldException;

}
