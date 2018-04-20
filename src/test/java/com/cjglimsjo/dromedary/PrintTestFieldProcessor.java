package com.cjglimsjo.dromedary;

public class PrintTestFieldProcessor implements Processor<TestObject> {

    @Override
    public void process(Exchange<TestObject> exchange) {
        TestObject testObject = exchange.getMessage();

        System.out.println(testObject + ": " + testObject.getTestField());
    }
}
