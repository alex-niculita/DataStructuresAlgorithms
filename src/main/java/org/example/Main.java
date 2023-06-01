package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        IntegerList integerList = new IntegerListImpl();

        integerList.add(7);
        integerList.add(2);
        integerList.add(1);
        integerList.add(5);
        integerList.add(4);
        integerList.add(9);
        integerList.add(11);
        integerList.add(118);
        integerList.add(6);

        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.size());

        integerList.add(3);
        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.size());

        integerList.add(8);
        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.size());

        System.out.println(integerList.contains(8));

//        long start = System.currentTimeMillis();
//        sortBubble(generateRandomArray());
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//        sortSelection(generateRandomArray());
//        System.out.println(System.currentTimeMillis() - start);
//
//        start = System.currentTimeMillis();
//        sortInsertion(generateRandomArray());
//        System.out.println(System.currentTimeMillis() - start);

    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
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

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static int[] generateRandomArray(){
        Random random = new Random();
        int[] arrRnd = new int[100_000];
        for (int i = 0; i < 100_000; i++) {
            arrRnd[i] = random.nextInt(100);
        }

        return arrRnd;
    }
}