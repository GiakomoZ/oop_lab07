package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private Collection<T> array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(Collection<T> array) {
        this(array, x -> true);
    }

    public IterableWithPolicyImpl(Collection<T> array, Predicate<T> filter) {
        this.array.addAll(array);
        this.filter = filter;
    }

    private static class InnerIterator<T> implements Iterator<T> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'next'");
        }

    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

}
