package org.example;

import java.util.*;
public class Main {


    public static void main(String[] args) {
        TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a -> a[0]));


        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int countOS = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < countOS; i++) {
            int[] operSystem = new int[2];
            operSystem[0] = scanner.nextInt();
            operSystem[1] = scanner.nextInt();
            scanner.nextLine();
            set.removeIf(a -> (a[0] >= operSystem[0] && a[0] <= operSystem[1]) || (a[1] >= operSystem[0] && a[0] <= operSystem[1]));
            set.add(operSystem);
        }

        System.out.println(set.size());
    }
}