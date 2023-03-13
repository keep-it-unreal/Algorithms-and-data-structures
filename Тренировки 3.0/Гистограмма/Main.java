package org.example;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int maxValue = 1;
        int curValue;
        Map<Character, Integer> map = new TreeMap<>();
        Path sourcePath = Paths.get("input.txt");
        try(BufferedReader reader = Files.newBufferedReader(sourcePath)) {
            int code;
            char ch;
            while ((code = reader.read()) != -1) {
                ch = (char) code;
                if(ch != '\n' && ch != ' ' && code!= 13) {
                    if (map.containsKey(ch)) {
                        curValue = map.get(ch);
                        map.put(ch, curValue + 1);
                        maxValue = Math.max(curValue + 1, maxValue);
                    } else {
                        map.put(ch, 1);
                    }
                }
            }
            }

        Set<Map.Entry<Character, Integer>> set = map.entrySet();

        while(maxValue != 0){
            for (Map.Entry<Character, Integer> e: set) {
                if(e.getValue() >= maxValue){
                    System.out.print('#');
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            --maxValue;
        }
        for (Map.Entry<Character, Integer> e: set) {
            System.out.print(e.getKey());
        }
        }
}
