package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countElements = scanner.nextInt();

        if(countElements == 1){
            System.out.println(2);
        } else if (countElements == 2) {
            System.out.println(4);
        } else if(countElements == 3){
            System.out.println(7);
        } else {
            int curValue = 3;
            int prevPrev = 2;
            int prev = 4;
            int cur = 7;
            int hub;
            while (curValue != countElements) {
                hub = cur;
                cur += prevPrev + prev;
                prevPrev = prev;
                prev = hub;
                ++curValue;
            }
            System.out.println(cur);
        }
    }
    //Через рекурсию не зашло по времени (35 элементов макс)
    public static int recursion(String str, int countOfElements){
        if(str.endsWith("111")){
            return 0;
        }
        if(countOfElements == 0){
            return 1;
        }
        return recursion(str + "1", countOfElements - 1) + recursion(str + "0", countOfElements - 1);
    }
}