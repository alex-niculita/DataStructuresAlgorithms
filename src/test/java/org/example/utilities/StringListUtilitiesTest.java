package org.example.utilities;

import org.example.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.utilities.StringListUtilities.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
class StringListUtilitiesTest {

    private final StringListUtilities stringListUtilities = new StringListUtilities();

    Element e0;
    Element e1;
    Element e2;

    @BeforeEach
    public void setup(){
        e0 = new Element("q0",0);
        e1 = new Element("q1",1);
        e2 = new Element("q2",2);

    }

    @Test
    void extendCapacity_validInput() {
        Element[] input = {e0,e1,e2};
        Element[] expected = {e0,e1,e2, null};
        assertArrayEquals(expected, stringListUtilities.extendCapacity(input));

        assertThat(stringListUtilities.extendCapacity(input)).containsExactly(expected);
    }

    @Test
    void extendCapacity_nullInput() {
        assertNull(stringListUtilities.extendCapacity(null));
    }

    @Test
    void shrinkCapacity_validInput() {
        Element[] input = {e0,e1,e2,null,null,null};
        Element[] expected = {e0,e1,e2};
        assertArrayEquals(expected, stringListUtilities.shrinkCapacity(input));
    }


    @Test
    void shrinkCapacity_invalidInput() {

        Element[] input = {e0,e1,e2,e0,e0,e0};
        Element[] expected = null;
        assertNull(stringListUtilities.shrinkCapacity(input));
    }


    @Test
    void shrinkCapacity_nullInput() {
        assertNull(stringListUtilities.shrinkCapacity(null));
    }

    @Test
    void findElementByValue_null() {
        assertNull(stringListUtilities.findElement(null,null));
    }

    @Test
    void findElementByValue_validInput() {

        Element[] input = {e0,e1,e2};

        Element expected = new Element("q1",1);

        assertThat(stringListUtilities.findElement(input,"q1")).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void findElementByValue_notFoundInput() {

        Element[] input = {e0,e1,e2};

        assertNull(stringListUtilities.findElement(input,"q3"));
    }

    @Test
    void findElementByIndex_validInput() {

        Element[] input = {e0,e1,e2};

        Element expected = new Element("q1",1);

        assertThat(stringListUtilities.findElement(input,1)).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void findElementByIndex_notFoundInput() {

        Element[] input = {e0,e1,e2};

        assertNull(stringListUtilities.findElement(input,4));
    }

    @Test
    void findLastElement_validInput() {

        Element e3 = new Element("q0",3);
        Element[] input = {e0,e1,e2,e3};

        Element expected = new Element("q0",3);

        assertThat(stringListUtilities.findLastElement(input,"q0")).usingRecursiveComparison().isEqualTo(expected);
    }

    public static Stream<Arguments> nullValues() {
        Element e0 = new Element("q0",0);
        Element e1 = new Element("q1",1);
        Element e2 = new Element("q2",2);
        Element e3 = new Element("q0",3);
        Element[] input = {e0,e1,e2,e3};
        return Stream.of(Arguments.of(input, null),
                Arguments.of(null, null),
                Arguments.of(null, "null"));
    }

    @ParameterizedTest
    @MethodSource("nullValues")
    void findLastElement_null(Element[] input, String str) {
        assertNull(stringListUtilities.findLastElement(input,str));
    }
}