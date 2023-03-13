package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Main {


    public static void main(String[] args) {
        Path path = Paths.get("input.txt");

        System.out.println(myReader(path));

    }

    public static String myReader(Path path){
        Deque<String> deque = new ArrayDeque<>();

        try(BufferedReader reader = Files.newBufferedReader(path)){
            int code;
            String inputBracket;
            String lastBracket;

            while ((code = reader.read()) != -1 && code != 13){
                inputBracket = (char) code + "";
                if(inputBracket.equals("(") || inputBracket.equals("[") || inputBracket.equals("{")){
                    deque.push(inputBracket);
                } else{
                    if(deque.isEmpty()){
                        return "no";
                    }
                    lastBracket = deque.getFirst();
                    if(lastBracket.equals("(")){
                        if(!inputBracket.equals(")")){
                            return "no";
                        } else {
                            deque.pop();
                        }
                    } else if (lastBracket.equals("[")) {
                        if(!inputBracket.equals("]")){
                            return "no";
                        } else {
                            deque.pop();
                        }
                    } else if (lastBracket.equals("{")) {
                        if(!inputBracket.equals("}")){
                            return "no";
                        } else {
                            deque.pop();
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        if(deque.isEmpty()) {
            return "yes";
        } else {
            return "no";
        }
    }
}