package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        int[][] array = new int[2][result + 1];
        array[0][1] = 1;
        while (array[0][result] == 0) {
            for (int i = 1; i <= result; ++i) {
                if (array[0][i] > 0) {
                    if (i + 1 <= result && (array[0][i + 1] == 0 || array[0][i + 1] > array[0][i])) {
                        array[0][i + 1] = array[0][i] + 1;
                        array[1][i + 1] = i;
                    }
                    if (i * 2 <= result && (array[0][i * 2] == 0 || array[0][i * 2] > array[0][i])) {
                        array[0][i * 2] = array[0][i] + 1;
                        array[1][i * 2] = i;
                    }
                    if (i * 3 <= result && (array[0][i * 3] == 0 || array[0][i * 3] > array[0][i])) {
                        array[0][i * 3] = array[0][i] + 1;
                        array[1][i * 3] = i;
                    }
                }
            }
        }
        System.out.println(array[0][result] - 1);
        List<Integer> list = new ArrayList<>();
        while (result != 1) {
            list.add(result);
            result = array[1][result];
        }
        list.add(1);
        for (int i = 1; i <= list.size(); i++) {
            System.out.print(list.get(list.size() - i) + " ");
        }
    }
}