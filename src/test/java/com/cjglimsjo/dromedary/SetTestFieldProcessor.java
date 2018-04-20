package com.cjglimsjo.dromedary;

import java.time.LocalDateTime;

public class SetTestFieldProcessor implements Processor<TestObject> {

    @Override
    public void process(Exchange<TestObject> exchange) {
        TestObject testObject = exchange.getMessage();

        testObject.setTestField(LocalDateTime.now().toString());
    }
}
