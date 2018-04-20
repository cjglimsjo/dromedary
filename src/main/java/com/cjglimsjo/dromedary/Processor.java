package com.cjglimsjo.dromedary;

@FunctionalInterface
public interface Processor<T> {

    void process(Exchange<T> exchange) throws Exception;
}
