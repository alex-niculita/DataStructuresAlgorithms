package org.example;

import org.assertj.core.api.Assertions;
import org.example.exemptions.NoSuchElementExemption;
import org.example.exemptions.NullElementExemption;
import org.example.exemptions.WrongIndexExemption;
import org.example.utilities.StringListUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StringListImplTest {

    @Mock
    StringListUtilities stringListUtilitiesMock;

    @InjectMocks
    StringListImpl out;

    Element e0;
    Element e1;
    Element e2;
    Element e3;
    Element e4;
    Element e5;
    Element e6;
    Element e7;
    Element e8;
    Element e9;

    Element[] input1;
    Element[] input2;
    @BeforeEach
    public void setup(){
        e0 = new Element("q0",0);
        e1 = new Element("q1",1);
        e2 = new Element("q2",2);
        e3 = new Element("q3",3);
        e4 = new Element("q4",4);
        e5 = new Element("q5",5);
        e6 = new Element("q6",6);
        e7 = new Element("q2",7);
        e8 = new Element("q8",8);
        e9 = new Element("q9",9);

        input1 = new Element[]{e0,e1,e2,null,null,null,null,null,null,null};
        input2 = new Element[]{e0,e1,e2,e3,e4,e5,e6,e7,e8,e9};

    }

    @Test
    void addNewElement_StringListNotFull() {

        Element e3 = new Element("q3",3);
        Element[] expected = {e0,e1,e2,e3,null,null,null,null,null,null};
        out.setStringList(input1);
        Assertions.assertThat(out.add("q3")).isEqualTo("q3");
        Assertions.assertThat(out.size()).isEqualTo(4);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);

    }

    @Test
    void addNewElement_NullStringInput() {
        out.setStringList(input1);
        assertThrows(NullElementExemption.class,()->out.add(null));

    }

    @Test
    void addNewElement_StringListFull() {
        when(stringListUtilitiesMock.extendCapacity(input2)).thenReturn(new Element[]{e0,e1,e2,e3,e4,e5,e6,e7,e8,e9,null,null,null,null,null});
        out.setStringList(input2);
        Element e10 = new Element("q3",10);
        Element[] expected = {e0,e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,null,null,null,null};

        Assertions.assertThat(out.add("q3")).isEqualTo("q3");
        Assertions.assertThat(out.getStringList()).containsExactly(expected).hasSize(15);
        Assertions.assertThat(out.size()).isEqualTo(11);
    }


    @Test
    void testAddAtExistingIndex_StringListNotFull() {
        Element e3 = new Element("q3",2);
        Element[] expected = {e0,e1,e3,e2,null,null,null,null,null,null};
        out.setStringList(input1);
        Assertions.assertThat(out.add(2,"q3")).isEqualTo("q3");
        Assertions.assertThat(out.size()).isEqualTo(4);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);
    }

    @Test
    void testAddAtExistingIndex_NullStringInput() {
        out.setStringList(input1);
        assertThrows(NullElementExemption.class,()->out.add(3,null));

    }

    @Test
    void testAddAtExistingIndex_WrongIndex() {
        out.setStringList(input1);
        assertThrows(WrongIndexExemption.class,()->out.add(6,"q1"));

    }

    @Test
    void testAddAtExistingIndex_StringListFull() {
        when(stringListUtilitiesMock.extendCapacity(input2)).thenReturn(new Element[]{e0,e1,e2,e3,e4,e5,e6,e7,e8,e9,null,null,null,null,null});
        out.setStringList(input2);
        Element e10 = new Element("q3",9);
        Element[] expected = {e0,e1,e2,e3,e4,e5,e6,e7,e8,e10,e9,null,null,null,null};

        Assertions.assertThat(out.add(9,"q3")).isEqualTo("q3");
        Assertions.assertThat(out.getStringList()).containsExactly(expected).hasSize(15);
        Assertions.assertThat(out.size()).isEqualTo(11);
    }

    @Test
    void set() {
        Element e3 = new Element("q3",1);
        Element[] expected = {e0,e3,e2,null,null,null,null,null,null,null};
        out.setStringList(input1);
        Assertions.assertThat(out.set(1,"q3")).isEqualTo("q3");
        Assertions.assertThat(out.size()).isEqualTo(3);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);
    }

    @Test
    void set_WrongIndex() {
        out.setStringList(input1);
        assertThrows(WrongIndexExemption.class,()->out.set(6,"q1"));
    }

    @Test
    void removeString() {
        when(stringListUtilitiesMock.findElement(input2,"q1")).thenReturn(new Element("q1",1));

        Element[] expected = {e0,e2,e3,e4,e5,e6,e7,e8,e9,null};
        out.setStringList(input2);
        Assertions.assertThat(out.remove("q1")).isEqualTo("q1");
        Assertions.assertThat(out.size()).isEqualTo(9);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);
    }

    @Test
    void removeString_shrinkCapacity() {
        when(stringListUtilitiesMock.findElement(input1,"q1")).thenReturn(new Element("q1",1));
        when(stringListUtilitiesMock.shrinkCapacity(input1)).thenReturn(new Element[]{e0,e2,null,null,null});
        Element[] expected = {e0,e2,null,null,null};
        out.setStringList(input1);
        Assertions.assertThat(out.remove("q1")).isEqualTo("q1");
        Assertions.assertThat(out.size()).isEqualTo(2);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);
    }

    @Test
    void removeString_nullInput() {
        out.setStringList(input1);
        assertThrows(NullElementExemption.class,()->out.remove(null));
    }

    @Test
    void removeString_nonExistingItem() {
        out.setStringList(input1);
        assertThrows(NoSuchElementExemption.class,()->out.remove("q1"));
    }

    @Test
    void removeByIndex() {
        when(stringListUtilitiesMock.findElement(input2,1)).thenReturn(new Element("q1",1));

        Element[] expected = {e0,e2,e3,e4,e5,e6,e7,e8,e9,null};
        out.setStringList(input2);
        Assertions.assertThat(out.remove(1)).isEqualTo("q1");
        Assertions.assertThat(out.size()).isEqualTo(9);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);
        Assertions.assertThat(e9.getIndex()).isEqualTo(8);
    }

    @Test
    void removeByIndex_shrinkCapacity() {
        when(stringListUtilitiesMock.findElement(input1,1)).thenReturn(new Element("q1",1));
        when(stringListUtilitiesMock.shrinkCapacity(input1)).thenReturn(new Element[]{e0,e2,null,null,null});
        Element[] expected = {e0,e2,null,null,null};
        out.setStringList(input1);
        Assertions.assertThat(out.remove(1)).isEqualTo("q1");
        Assertions.assertThat(out.size()).isEqualTo(2);
        Assertions.assertThat(out.getStringList()).containsExactly(expected);
    }

    @Test
    void removeByIndex_nonExistingIndex() {
        out.setStringList(input1);
        assertThrows(WrongIndexExemption.class,()->out.remove(6));
    }

    @Test
    void contains_true() {
        when(stringListUtilitiesMock.findElement(input2,"q1")).thenReturn(new Element("q1",1));
        out.setStringList(input2);
        assertTrue(out.contains("q1"));
    }

    @Test
    void contains_false() {
        when(stringListUtilitiesMock.findElement(input2,"q111")).thenReturn(null);
        out.setStringList(input2);
        assertFalse(out.contains("q111"));
    }

    @Test
    void contains_null() {
        out.setStringList(input2);
        assertThrows(NullElementExemption.class, ()->out.contains(null));
    }

    @Test
    void indexOf() {
        when(stringListUtilitiesMock.findElement(input2,"q2")).thenReturn(new Element("q2",2));
        out.setStringList(input2);
        Assertions.assertThat(out.indexOf("q2")).isEqualTo(2);
    }

    @Test
    void indexOf_NonExistentItem() {
        when(stringListUtilitiesMock.findElement(input2,"q55")).thenReturn(null);
        out.setStringList(input2);
        Assertions.assertThat(out.indexOf("q55")).isEqualTo(-1);
    }

    @Test
    void indexOf_NullItem() {
        out.setStringList(input2);
        assertThrows(NullElementExemption.class, ()->out.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        when(stringListUtilitiesMock.findLastElement(input2,"q2")).thenReturn(new Element("q2",7));
        out.setStringList(input2);
        Assertions.assertThat(out.lastIndexOf("q2")).isEqualTo(7);
    }

    @Test
    void get() {
        when(stringListUtilitiesMock.findElement(input2,2)).thenReturn(new Element("q2",2));
        out.setStringList(input2);
        Assertions.assertThat(out.get(2)).isEqualTo("q2");
    }

    public static Stream<Arguments> provideForGet(){
        return Stream.of(Arguments.of(-1),
                Arguments.of(40),
                Arguments.of(1000));
    }
    @ParameterizedTest
    @MethodSource("provideForGet")
    void get_InvalidInput1(int i) {
        out.setStringList(input2);
        assertThrows(WrongIndexExemption.class, ()->out.get(i));
    }

    @Test
    void testEquals_true() {
        StringListImpl stringListTest = new StringListImpl();
        Element[] inputTest = new Element[]{e0,e1,e2,null,null,null,null,null,null,null};
        stringListTest.setStringList(inputTest);

        when(stringListUtilitiesMock.findElement(inputTest,0)).thenReturn(new Element("q0",0));
        when(stringListUtilitiesMock.findElement(inputTest,1)).thenReturn(new Element("q1",1));
        when(stringListUtilitiesMock.findElement(inputTest,2)).thenReturn(new Element("q2",2));

        out.setStringList(input1);

        assertTrue(out.equals(stringListTest));

    }

    @Test
    void testEquals_false() {
        StringListImpl stringListTest = new StringListImpl();
        Element[] inputTest = new Element[]{e0,e5,e2,null,null,null,null,null,null,null};
        stringListTest.setStringList(inputTest);

        when(stringListUtilitiesMock.findElement(inputTest,0)).thenReturn(new Element("q0",0));
        when(stringListUtilitiesMock.findElement(inputTest,1)).thenReturn(new Element("q5",5));

        out.setStringList(input1);

        assertFalse(out.equals(stringListTest));

    }

    @Test
    void testEquals_Null() {
        out.setStringList(input2);
        assertThrows(NullElementExemption.class, ()->out.equals(null));
    }

    public static Stream<Arguments> provideForSize(){
        return Stream.of(Arguments.of(0,new Element[]{null,null,null,null,null,null,null,null,null,null}),
                Arguments.of(0, new Element[]{}),
                Arguments.of(1,new Element[]{new Element("w0",0),null,null,null,null,null,null,null,null,null}),
                Arguments.of(3,new Element[]{new Element("w0",0),new Element("w1",1),new Element("w2",2),null,null,null,null,null,null,null}));
    }
    @ParameterizedTest
    @MethodSource("provideForSize")
    void size(int expected, Element[] strList) {
        out.setStringList(strList);
        assertEquals(expected, out.size());
    }

    @Test
    void isEmpty_true1() {
        out.setStringList(new Element[]{});
        assertTrue(out.isEmpty());
    }

    @Test
    void isEmpty_true2() {
        out.setStringList(new Element[]{null, null, null});
        assertTrue(out.isEmpty());
    }

    @Test
    void isEmpty_false() {
        out.setStringList(input1);
        assertFalse(out.isEmpty());
    }

    @Test
    void clear() {
        out.setStringList(input1);
        out.clear();
        Assertions.assertThat(out.size()).isEqualTo(0);
    }

    @Test
    void toArray() {
        out.setStringList(input1);
        String[] expected = {"q0","q1","q2"};
        Assertions.assertThat(out.toArray()).containsExactly(expected);
    }

    @Test
    void toArray_empty() {
        out.setStringList(new Element[]{null, null, null});
        String[] expected = {};
        Assertions.assertThat(out.toArray()).containsExactly(expected);
    }
}