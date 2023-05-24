package org.example.utilities;

import org.example.Element;

public class StringListUtilities {

    // extend the capacity by ~50% of our initial array if more space needed
    public Element[] extendCapacity(Element[] arr) {
        if (arr == null) return null;
        Element[] newArr = new Element[arr.length + (arr.length / 2)];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    // reduce the capacity by ~50% of our initial array if no more space needed
    public Element[] shrinkCapacity(Element[] arr) {
        if (arr == null) return null;
        Element[] newArr = new Element[arr.length / 2];

        System.arraycopy(arr, 0, newArr, 0, arr.length / 2);
        return newArr;
    }

    public Element findElement(Element[] arr, String value) {

        if (arr == null || value == null) return null;

        for (Element e : arr) {
            if (e.getValue().equals(value)) return e;
        }
        return null;
    }

    public Element findElement(Element[] arr, int index) {

        if (arr == null) return null;

        for (Element e : arr) {
            if (e.getIndex() == index) return e;
        }
        return null;
    }

    public Element findLastElement(Element[] arr, String value) {

        if (arr == null || value == null) return null;

        for (int i = arr.length - 1; i >= 0; i--){
            if(arr[i] == null) continue;
            if (arr[i].getValue().equals(value)) return arr[i];
        }
        return null;
    }


}
