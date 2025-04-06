package kg.geeks.coolband.services;

public interface OneEntityBaseService<Z,T> {
    Z get();
    Z patch(T request) throws IllegalAccessException, NoSuchFieldException;
}
