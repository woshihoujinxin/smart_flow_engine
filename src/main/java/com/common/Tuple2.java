package com.common;

/**
 * Represents a list of 2 typed Objects.
 */
public class Tuple2<T1, T2> extends Tuple {
    public Tuple2(T1 first, T2 second) {
        super(new Object[]{first, second});
    }

    @SuppressWarnings("unchecked")
    public T1 getFirst() {
        return (T1) get(0);
    }

    @SuppressWarnings("unchecked")
    public T2 getSecond() {
        return (T2) get(1);
    }
}
