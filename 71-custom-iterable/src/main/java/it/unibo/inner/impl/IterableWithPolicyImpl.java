package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] array) {
        this(array, new Predicate<>() {
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(T[] array, Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }

    private static class InnerIterator<T> implements Iterator<T> {

        private int index;
        private T[] array;
        private Predicate<T> filter;

        public InnerIterator(T[] array, Predicate<T> filter) {
            this.index = 0;
            this.array = array;
            this.filter = filter;
        }

        @Override
        public boolean hasNext() {
            while (index < this.array.length && !this.filter.test(this.array[index])) {
                index++;
            }
            return this.index < this.array.length ? true : false;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                T toReturn = this.array[index];
                this.index++;
                return toReturn;
            } else {
                throw new NoSuchElementException();
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator<>(array, filter);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

}
