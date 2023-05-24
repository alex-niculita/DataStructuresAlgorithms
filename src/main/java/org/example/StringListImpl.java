package org.example;

import org.example.exemptions.NoSuchElementExemption;
import org.example.exemptions.NullElementExemption;
import org.example.exemptions.WrongIndexExemption;
import org.example.utilities.StringListUtilities;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.example.utilities.StringListUtilities.*;

public class StringListImpl implements StringList {

    StringListUtilities stringListUtilities = new StringListUtilities();

    private Element[] stringList;
    private int capacity = 10;

    public StringListImpl(){
        // Default capacity of our array
        stringList = new Element[capacity];
    }


    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {

        // Check null input
        if (item == null) throw new NullElementExemption("Null is not allowed here");

        //Check if capacity is enough, if not add more
        if (size() == capacity) {
            stringList = stringListUtilities.extendCapacity(stringList);
            setCapacity(stringList.length);
        }

        int index = size();
        Element element = new Element(item, index);
        stringList[index] = element;

        return stringList[index].getValue();
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(int index, String item) {

        // Check null input
        if (item == null) throw new NullElementExemption("Null is not allowed here");

        // index validation
        if (index < 0 || index >= size()) throw new WrongIndexExemption("Wrong Index! Choose index from 0 to " + (size()-1) + "(inclusive)!");

        //Check if capacity is enough, if not add more
        if (size() == capacity) {
            stringList = stringListUtilities.extendCapacity(stringList);
            setCapacity(stringList.length);
        }
        Element element = new Element(item, index);

        for (int i = size(); i >= 0; i--) {
            if (i > index) {

                stringList[i] = stringList[i-1];
                stringList[i].setIndex(stringList[i-1].getIndex()+1);
            } else if (i == index) {
                stringList[i] = element;
            } else {
                break;
            }
        }
        return stringList[index].getValue();
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public String set(int index, String item) {

        // Check null input
        if (item == null) throw new NullElementExemption("Null is not allowed here");

        // index validation
        if (index < 0 || index >= size()) throw new WrongIndexExemption("Wrong Index! Choose index from 0 to " + (size()-1) + "(inclusive)!");

        Element element = new Element(item, index);

        for (int i = 0; i < size(); i++) {
            if (i == index) {
                stringList[i] = element;
            }
        }

        return stringList[index].getValue();
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {

        // Check null input
        if (item == null) throw new NullElementExemption("Null is not allowed here");

        Element element = stringListUtilities.findElement(stringList,item);
        if (element == null) throw new NoSuchElementExemption("Item '"+ item +"' is not found!");



        for (int i = 0; i < size(); i++) {
            if (i == size()-1) {
                stringList[i] = null;
                break;
            }

            if (i >= element.getIndex()) {
                stringList[i] = stringList[i+1];
                stringList[i].setIndex(stringList[i+1].getIndex()-1);
            }

        }

        //Check capacity, if too much then shrink array length
        if ((size()) <= capacity/2) {
            stringList = stringListUtilities.shrinkCapacity(stringList);
            setCapacity(stringList.length);
        }

        return element.getValue();
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        // index validation
        if (index < 0 || index >= size()) throw new WrongIndexExemption("Wrong Index! Choose index from 0 to " + (size()-1) + "(inclusive)!");

        Element element = stringListUtilities.findElement(stringList, index);

        if (element == null) throw new NoSuchElementExemption("Item with index "+ index +" is not found!");



        for (int i = 0; i < size(); i++) {
            if (i == size()-1) {
                stringList[i] = null;
                break;
            }

            if (i >= index) {
                stringList[i] = stringList[i+1];
                stringList[i].setIndex(stringList[i+1].getIndex()-1);
            }

        }

        //Check capacity, if too much then shrink array length
        if ((size()) <= capacity/2) {
            stringList = stringListUtilities.shrinkCapacity(stringList);
            setCapacity(stringList.length);
        }

        return element.getValue();

    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        // Check null input
        if (item == null) throw new NullElementExemption("Null is not allowed here");

        return stringListUtilities.findElement(stringList, item) != null;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        // Check null input
        if (item == null) throw new NullElementExemption("Null is not allowed here");

        return (stringListUtilities.findElement(stringList, item) != null)?stringListUtilities.findElement(stringList, item).getIndex():-1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        return (stringListUtilities.findLastElement(stringList, item) != null)?stringListUtilities.findLastElement(stringList, item).getIndex():-1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index) {
        // index validation
        if (index < 0 || index >= size()) throw new WrongIndexExemption("Wrong Index! Choose index from 0 to " + (size()-1) + "(inclusive)!");

        return stringListUtilities.findElement(stringList, index).getValue();
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList) {

        if (otherList == null) throw new NullElementExemption("Input array is null!");

        if (size() != otherList.size()) return false;

        for (int i = 0; i < size(); i++) {
            if (!stringList[i].equals(stringListUtilities.findElement(otherList.getStringList(), i))) return false;
        }

        return true;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        int i = 0;
        for (;i<stringList.length;i++){
            if (stringList[i] == null) break;
        }
        return i;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        setCapacity(10);
        stringList = new Element[capacity];
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        return Arrays.stream(stringList).filter(e->e != null).map(e->e.getValue()).toArray(String[]::new);
    }


    // Getters & Setters

    public Element[] getStringList() {
        return stringList;
    }

    public void setStringList(Element[] stringList) {
        this.stringList = stringList;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
