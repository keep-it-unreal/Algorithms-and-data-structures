package org.example;

import java.util.*;

public class Main {
    public static int[] array;
    public static void main(String[] args) {
        int minCountOfLetterIndex;
        long result;
        Scanner scanner = new Scanner(System.in);
        int countOfLetter = scanner.nextInt();
        array = new int[countOfLetter];
        scanner.nextLine();
        minCountOfLetterIndex = 0;
        for (int i = 0; i < countOfLetter; i++) {
            array[i] = scanner.nextInt();
            scanner.nextLine();
            if (array[i] < array[minCountOfLetterIndex]) {
                minCountOfLetterIndex = i;
            }
        }

        result = (long)array[minCountOfLetterIndex] * (countOfLetter - 1);

        result += recursion(0, minCountOfLetterIndex, array[minCountOfLetterIndex]) +
                recursion(minCountOfLetterIndex + 1, countOfLetter, array[minCountOfLetterIndex]);

        System.out.println(result);
    }

    public static long recursion(int begin, int finish, int sumOfMinus) {
        if(begin == finish){
            return 0;
        }
        int secondFlag = begin + 1;
        int minCountOfLetterIndex = begin;
        while (secondFlag != finish){
            if(array[secondFlag] < array[minCountOfLetterIndex]){
                minCountOfLetterIndex = secondFlag;
            }
            ++secondFlag;
        }
        int newSumOfMinus;
        if(array[minCountOfLetterIndex] - sumOfMinus < 0){
            newSumOfMinus = sumOfMinus;
        } else {
            newSumOfMinus = array[minCountOfLetterIndex];
        }
        long a = (long) Math.max((array[minCountOfLetterIndex] - sumOfMinus), 0) * (secondFlag - begin - 1);
        long b = recursion(begin, minCountOfLetterIndex, newSumOfMinus);
        long c = recursion(minCountOfLetterIndex + 1, finish, newSumOfMinus);
    return a + b + c;
    }
}

/**
 * long max;
 *         if(array[minCountOfLetterIndex] - sumOfMinus > 0){
 *             max = array[minCountOfLetterIndex] - sumOfMinus;
 *         } else {
 *             max = 0L;
 *         }
 *         long a = max * (secondFlag - begin - 1);
 */