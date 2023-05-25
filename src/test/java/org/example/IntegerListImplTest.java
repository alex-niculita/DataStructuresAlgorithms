package org.example;

import org.assertj.core.api.Assertions;
import org.example.exemptions.ArrayFullExemption;
import org.example.exemptions.NullElementExemption;
import org.example.exemptions.WrongIndexExemption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private IntegerList integerListEmpty;
    private IntegerList integerListFull;
    @BeforeEach
    public void setup(){
        integerListEmpty = new IntegerListImpl();
        integerListFull = new IntegerListImpl();
        integerListFull.add(0);
        integerListFull.add(1);
        integerListFull.add(2);
        integerListFull.add(3);
        integerListFull.add(4);
        integerListFull.add(5);
        integerListFull.add(6);
        integerListFull.add(7);
        integerListFull.add(8);
        integerListFull.add(9);
    }
    public static Stream<Arguments> provideArgsForAdd() {
        return Stream.of(
                Arguments.of(0,0),
                Arguments.of(-1,-1),
                Arguments.of(700,700),
                Arguments.of(100,100),
                Arguments.of(5,5));
    }
    @ParameterizedTest
    @MethodSource("provideArgsForAdd")
    void add(Integer expected, Integer actual) {
        Assertions.assertThat(integerListEmpty.add(actual)).isEqualTo(expected);
        Assertions.assertThat(integerListEmpty.size()).isEqualTo(1);
    }

    @Test
    void add_null() {
        assertThrows(NullElementExemption.class,()->integerListEmpty.add(null));
    }

    @Test
    void add_arrayFull() {
        assertThrows(ArrayFullExemption.class,()->integerListFull.add(1));
    }

    @Test
    void addByIndex_arrEmpty() {
        Assertions.assertThat(integerListEmpty.add(0,7)).isEqualTo(7);
        Assertions.assertThat(integerListEmpty.size()).isEqualTo(1);
    }

    @Test
    void addByIndex_insertLast() {
        integerListEmpty.add(0);
        integerListEmpty.add(1);
        integerListEmpty.add(2);
        integerListEmpty.add(3);
        integerListEmpty.add(4);
        integerListEmpty.add(5);
        integerListEmpty.add(6);
        integerListEmpty.add(7);
        integerListEmpty.add(8);
        Assertions.assertThat(integerListEmpty.add(9,11)).isEqualTo(11);
        Assertions.assertThat(integerListEmpty.size()).isEqualTo(10);
    }

    @Test
    void addByIndex_insertInTheMiddle() {
        integerListEmpty.add(0);
        integerListEmpty.add(1);
        integerListEmpty.add(2);
        integerListEmpty.add(3);
        integerListEmpty.add(4);
        integerListEmpty.add(5);
        integerListEmpty.add(6);
        integerListEmpty.add(7);

        Assertions.assertThat(integerListEmpty.add(4,11)).isEqualTo(11);
        Assertions.assertThat(integerListEmpty.size()).isEqualTo(9);
    }

    @Test
    void addByIndex_arrFull() {
        assertThrows(ArrayFullExemption.class,()->integerListFull.add(2,1));
    }

    @Test
    void addByIndex_wrongIndex() {
        assertThrows(WrongIndexExemption.class,()->integerListFull.add(11,5));
    }

    @Test
    void addByIndex_itemNull() {
        assertThrows(NullElementExemption.class,()->integerListFull.add(1, null));
    }

    @Test
    void set_arrEmpty() {
        Assertions.assertThat(integerListEmpty.set(0,11)).isEqualTo(11);
    }

    @Test
    void set_arrFull() {
        Assertions.assertThat(integerListFull.set(5,11)).isEqualTo(11);
        Assertions.assertThat(integerListFull.size()).isEqualTo(10);
    }

    @Test
    void set_wrongIndex() {
        assertThrows(WrongIndexExemption.class,()->integerListFull.set(11,5));
    }

    @Test
    void set_itemNull() {
        assertThrows(NullElementExemption.class,()->integerListFull.set(1, null));
    }

    @Test
    void remove() {
        Assertions.assertThat(integerListFull.remove(Integer.valueOf(5))).isEqualTo(5);
        Assertions.assertThat(integerListFull.size()).isEqualTo(9);
    }

    @Test
    void removeByIndex_wrongItem() {
        assertThrows(WrongIndexExemption.class,()->integerListFull.remove(Integer.valueOf(15)));
    }

    @Test
    void remove_itemNull() {
        assertThrows(NullElementExemption.class,()->integerListFull.remove(null));
    }

    @Test
    void removeByIndex() {
        Assertions.assertThat(integerListFull.remove(5)).isEqualTo(5);
        Assertions.assertThat(integerListFull.size()).isEqualTo(9);
    }

    @Test
    void removeByIndex_wrongIndexBorder() {
        assertThrows(WrongIndexExemption.class,()->integerListFull.remove(10));
    }

    @Test
    void removeByIndex_wrongIndex() {
        assertThrows(WrongIndexExemption.class,()->integerListFull.remove(35));
    }

    @Test
    void contains_true() {
        assertTrue(integerListFull.contains(5));
    }

    @Test
    void contains_false() {
        assertFalse(integerListFull.contains(15));
    }

    @Test
    void contains_itemNull() {
        assertThrows(NullElementExemption.class,()->integerListFull.contains(null));
    }

    @Test
    void indexOf() {
        Assertions.assertThat(integerListFull.indexOf(5)).isEqualTo(5);
    }

    @Test
    void indexOf_wrongItem() {
        Assertions.assertThat(integerListFull.indexOf(15)).isEqualTo(-1);
    }

    @Test
    void indexOf_itemNull() {
        assertThrows(NullElementExemption.class,()->integerListFull.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        integerListFull.set(8,2);
        Assertions.assertThat(integerListFull.lastIndexOf(2)).isEqualTo(8);
    }

    @Test
    void get() {
        Assertions.assertThat(integerListFull.get(2)).isEqualTo(2);
    }

    @Test
    void get_wrongIndex() {
        assertThrows(WrongIndexExemption.class,()->integerListFull.get(35));
    }

    @Test
    void testEquals_true() {
        IntegerList testList = new IntegerListImpl();
        testList.add(0);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        integerListEmpty.add(0);
        integerListEmpty.add(1);
        integerListEmpty.add(2);
        integerListEmpty.add(3);

        assertTrue(integerListEmpty.equals(testList));

    }

    @Test
    void size_empty() {
        assertEquals(0,integerListEmpty.size());
    }

    @Test
    void size_full() {
        assertEquals(10,integerListFull.size());
    }

    @Test
    void size() {
        integerListEmpty.add(0);
        integerListEmpty.add(1);
        integerListEmpty.add(2);
        integerListEmpty.add(3);
        assertEquals(4,integerListEmpty.size());
    }

    @Test
    void isEmpty_true() {
        assertTrue(integerListEmpty.isEmpty());
    }

    @Test
    void isEmpty_false() {
        assertFalse(integerListFull.isEmpty());
    }

    @Test
    void clear() {
        integerListFull.clear();
        assertEquals(0,integerListFull.size());
    }

    @Test
    void toArray() {
        Integer[] expected = {0,1,2,3};
        integerListEmpty.add(0);
        integerListEmpty.add(1);
        integerListEmpty.add(2);
        integerListEmpty.add(3);

        assertArrayEquals(expected,integerListEmpty.toArray());
    }
}