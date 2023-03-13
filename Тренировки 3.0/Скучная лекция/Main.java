package org.example;

import java.util.*;
public class Main {

    public static void main(String[] args) {

        long[] array = new long[26];

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        int firstFlag = 0;
        int secondFlag = str.length() - 1;

        long sumValue = str.length();
        array[(int)str.charAt(firstFlag) - 97] += sumValue;
        array[(int)str.charAt(secondFlag) - 97] += sumValue;
        ++firstFlag;
        --secondFlag;

        long x = 1;

        while (firstFlag < secondFlag){
            sumValue += str.length() - firstFlag - x;
            array[(int)str.charAt(firstFlag) - 97] += sumValue;
            array[(int)str.charAt(secondFlag) - 97] += sumValue;
            ++x;
            ++firstFlag;
            --secondFlag;
        }

        if(firstFlag == secondFlag){
            sumValue += str.length() - firstFlag - x;
            array[(int)str.charAt(firstFlag) - 97] += sumValue;
        }

        char ch;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > 0) {
                ch = (char)(i + 97);
                System.out.println(ch + ": " + array[i]);
            }
        }
    }
}