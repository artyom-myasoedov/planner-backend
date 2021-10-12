package ru.vsu.service.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TetraFunction<A, B, C, D, E> {

    E apply(A a, B b, C c, D d);


    default <V> TetraFunction<A, B, C, D, V> andThen(Function<? super E, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c, D d) -> after.apply(apply(a, b, c, d));
    }
}
