package org.example;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        int curValue;
        int lastValue = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        int countOfDigit = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < countOfDigit; ++i){
            curValue = scanner.nextInt();
            if(curValue == lastValue + 1){
                lastValue = curValue;
                while (!deque.isEmpty()){
                    if(deque.getFirst() == lastValue + 1){
                        lastValue = deque.pop();
                    } else{
                        break;
                    }
                }
            } else {
                deque.push(curValue);
            }
        }

        if(deque.isEmpty()){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}