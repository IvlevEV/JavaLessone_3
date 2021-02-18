package Lessone01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // Задача 1. Написать метод, который меняет два элемента массива местами.
        // (массив может быть любого ссылочного типа);
        String[] arr = {"asd", "ds", "ret", "123"};
        Integer[] arr2 = {3, 4, 6, 234, -1324, 111};

        System.out.println("Task 1\n" + Arrays.toString(arr));
        swapElementsArray(arr, 2, 3);
        System.out.println(Arrays.toString(arr));
        System.out.println("Task 1\n" + Arrays.toString(arr2));
        swapElementsArray(arr2, 2, 3);
        System.out.println(Arrays.toString(arr2));


        //Задача 2. Написать метод, который преобразует массив в ArrayList
        List<String> list = convertToList(arr);
        System.out.println("Task 2\n" + list.getClass() + " : " + list);
        List<Integer> list1 =  convertToList(arr2);
        System.out.println("Task 2\n" + list1.getClass() + " : " + list);


        //Задача 3. Коробки с фруктами
        System.out.println("Task 3");
        Orange orange = new Orange();
        Apple apple = new Apple();
        Box<Orange> orangeBox1 = new Box();
        Box<Orange> orangeBox2 = new Box();
        Box<Apple> appleBox = new Box();
        orangeBox1.add(new Orange(), 3);
        orangeBox1.add(new Orange(),1);
        orangeBox1.add(new Orange(),4);

        orangeBox2.add(new Orange(),7);
        appleBox.add(new Apple(), 5);

        orangeBox1.info();
        orangeBox2.info();
        appleBox.info();

        Float orange1Weigth = orangeBox1.getWeight();
        Float orange2Weigth = orangeBox2.getWeight();
        Float appleWeigth = appleBox.getWeight();
        System.out.println("Вес коробки 1 с апельсинами: " + orange1Weigth);
        System.out.println("Вес коробки 2 с апельсинами: " + orange2Weigth);
        System.out.println("Вес коробки с яблоками: " + appleWeigth);

        System.out.println("Сравнить вес orangeBox1 и appleBox: " + orangeBox1.compare(appleBox));
        System.out.println("Сравнить вес orangeBox2 и appleBox: " + orangeBox2.compare(orangeBox1));

        orangeBox1.moveAt(orangeBox2);
//            orangeBox1.moveAt(appleBox);   Error

        orangeBox1.info();
        orangeBox2.info();
        appleBox.info();
    }

    private static <T> void swapElementsArray(T[] array, int index1, int index2) {
        if (array != null && index1 < array.length && index1 >= 0 && index2 < array.length && index2 >=0 && index1 != index2)
        {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        } else System.err.println("Ошибка либо нецелеобразность обработки перестановки элементов массива.");
    }

    private static <T> List<T> convertToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }


}