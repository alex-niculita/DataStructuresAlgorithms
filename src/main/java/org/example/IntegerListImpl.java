package org.example;

import org.example.exemptions.ArrayFullExemption;
import org.example.exemptions.NoSuchElementExemption;
import org.example.exemptions.NullElementExemption;
import org.example.exemptions.WrongIndexExemption;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final int CAPACITY = 10;
    private Integer[] integers = new Integer[CAPACITY];

    private int size;

    @Override
    public Integer add(Integer item) {
        checkNull(item);
        checkCapacity();
        integers[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkNull(item);
        checkIndex(index);
        checkCapacity();

//        if (index == size) {
//            integers[size] = item;
//            size++;
//            return item;
//        }

        System.arraycopy(integers, index, integers, index+1,size-index);
        integers[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNull(item);
        checkIndex(index);
        integers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkNull(item);
        int index = indexOf(item);

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndexForRemove(index);

        Integer item = integers[index];

        if (index != size){
            System.arraycopy(integers,index +1 ,integers, index, size-1-index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkNull(item);
        Integer[] tempArr = this.toArray();
        sortInsertion(tempArr);
        return binarySearch(tempArr, item);
    }

    @Override
    public int indexOf(Integer item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(integers[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(integers[i])) return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        integers = new Integer[CAPACITY];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    private void checkNull(Integer item) {
        if (item == null) throw new NullElementExemption("Null is not allowed!");
    }

    private void checkCapacity() {
        if (size == CAPACITY) throw new ArrayFullExemption("List is full. You can't add more items!");
    }

    private void checkIndex(int index) {
        if (index <0 || index > size) throw new WrongIndexExemption("Index " + index + " is not correct!");
    }

    private void checkIndexForRemove(int index) {
        if (index <0 || index >= size) throw new WrongIndexExemption("Index " + index + " is not correct!");
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
