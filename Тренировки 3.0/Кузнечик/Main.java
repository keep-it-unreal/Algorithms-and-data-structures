package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countOfCell = scanner.nextInt();
        int jumpLength = scanner.nextInt();
        if (countOfCell == 1) {
            System.out.println(1);
        } else {
            int[] array = new int[jumpLength + countOfCell];

            array[jumpLength] = 1;
            array[jumpLength + 1] = 1;

            for (int i = 2; i < countOfCell; i++) {
                for (int j = 1; j <= jumpLength; j++) {
                    array[i + jumpLength] += array[i + jumpLength - j];
                }
            }

            System.out.println(array[array.length - 1]);
        }
    }
}