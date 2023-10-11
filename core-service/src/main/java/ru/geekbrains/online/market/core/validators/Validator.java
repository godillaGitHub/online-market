package ru.geekbrains.online.market.core.validators;

public interface Validator<E> {
    void validate(E e);
}
