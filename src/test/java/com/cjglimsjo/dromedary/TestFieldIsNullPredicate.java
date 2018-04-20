package com.cjglimsjo.dromedary;

public class TestFieldIsNullPredicate implements Predicate<TestObject> {

    @Override
    public boolean matches(Exchange<TestObject> exchange) {
        TestObject testObject = exchange.getMessage();

        return testObject.getTestField() == null;
    }
}
