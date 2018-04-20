package com.cjglimsjo.dromedary;

@FunctionalInterface
public interface Predicate<T> {

    boolean matches(Exchange<T> exchange);
}
