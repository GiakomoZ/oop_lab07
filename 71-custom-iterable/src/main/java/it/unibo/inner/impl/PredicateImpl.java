package it.unibo.inner.impl;

import java.util.function.Predicate;

public class PredicateImpl<T> implements Predicate<T> {

    @Override
    public boolean test(T t) {
        return true;
    }

}
