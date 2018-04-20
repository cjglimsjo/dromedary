package com.cjglimsjo.dromedary;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DromedaryTest {

    @Test
    public void test() {
        List<TestObject> testObjects = Arrays.asList(new TestObject(null), new TestObject("Test value"));

        Collection<TestObject> processedTestObjects = new Route<>(testObjects)
                .process(new PrintTestFieldProcessor())
                .startChoiceRoute()
                    .choice(new TestFieldIsNullPredicate())
                        .process(new SetTestFieldProcessor())
                    .endChoice()
                .endChoiceRoute()
                .process(new PrintTestFieldProcessor())
                .returnValid();

        assertEquals(2, processedTestObjects.size());
        assertTrue(processedTestObjects.stream().allMatch(testObject -> testObject.getTestField() != null));
    }
}
