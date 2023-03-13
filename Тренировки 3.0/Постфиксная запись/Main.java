package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Main {
    static Deque<String> deque;

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        deque = new ArrayDeque<>();
        try(BufferedReader reader = Files.newBufferedReader(path)){
            int code;
            StringBuilder curSymbol = new StringBuilder();

            while ((code = reader.read()) != -1 && code != 13 && code != 10){
                if(code == 32 && !curSymbol.toString().equals("")){
                    if(Character.isDigit(curSymbol.charAt(0))){
                        deque.push(curSymbol.toString());
                    } else {
                        summing(curSymbol);
                    }
                    curSymbol = new StringBuilder();
                } else if(code == 32 && curSymbol.toString().equals("")){
                    break;
                } else {
                    curSymbol.append((char) code);
                }
            }
            if(!curSymbol.toString().equals("")){
                summing(curSymbol);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.println(deque.pop());
    }

    public static void summing(StringBuilder curSymbol) {
        int secondValue;
        int firstValue;
        int curValue;
        secondValue = Integer.parseInt(deque.pop());
        firstValue = Integer.parseInt(deque.pop());
        if(curSymbol.toString().equals("+")){
            curValue = firstValue + secondValue;
        }else if(curSymbol.toString().equals("-")){
            curValue = firstValue - secondValue;
        } else {
            curValue = firstValue * secondValue;
        }
        deque.push(String.valueOf(curValue));
    }
}