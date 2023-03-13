package org.example;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array1 = scanner.nextLine().split(" ");
        String[] array2 = scanner.nextLine().split(" ");
        Queue<Integer> queue1 = new ArrayDeque<>();
        Queue<Integer> queue2 = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            queue1.add(Integer.parseInt(array1[i]));
            queue2.add(Integer.parseInt(array2[i]));
        }

        int valueOfMoves = 0;
        int first, second;
        while (!queue1.isEmpty() && !queue2.isEmpty() && valueOfMoves <= 1000000){
            first = queue1.remove();
            second = queue2.remove();
            if(first == 0 && second == 9){
                queue1.add(first);
                queue1.add(second);
            } else if (first == 9 && second == 0) {
                queue2.add(first);
                queue2.add(second);
            }else if(first > second){
                queue1.add(first);
                queue1.add(second);
            } else {
                queue2.add(first);
                queue2.add(second);
            }
            valueOfMoves++;
        }

        if(valueOfMoves == 1000001){
            System.out.println("botva");
        } else {
            if(queue1.isEmpty()){
                System.out.println("second " + valueOfMoves);
            } else {
                System.out.println("first " + valueOfMoves);
            }
        }
    }
}