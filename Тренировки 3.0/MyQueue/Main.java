package org.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path input = Paths.get("input.txt");
        Path output = Paths.get("output.txt");
        try (BufferedReader reader = Files.newBufferedReader(input);
             BufferedWriter writer = Files.newBufferedWriter(output)) {
            String str;
            MyQueue myQueue = new MyQueue();
            String[] arrayFromPush = new String[2];

            while (true) {
                str = reader.readLine();
                if (str.equals("pop")) {
                    writer.write(myQueue.pop());
                } else if (str.equals("front")) {
                    writer.write(myQueue.front());
                } else if (str.equals("size")) {
                    writer.write(myQueue.size());
                } else if (str.equals("clear")) {
                    writer.write(myQueue.clear());
                } else if (str.equals("exit")) {
                    break;
                } else {
                    arrayFromPush = str.split(" ");
                    writer.write(myQueue.push(Integer.parseInt(arrayFromPush[1])));
                }
                writer.newLine();
            }
            writer.write("bye");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

class MyQueue{

    int[] array;
    long front;
    long back;

    public MyQueue(){
        array = new int[15];
        front = 0;
        back = 0;
    }

    public String push(int n){
        if(front % array.length == (back + 1) % array.length){
            int[] newArray = new int[array.length * 2];
            for(int i = 0; i < array.length - 1; ++i){
                newArray[i] = array[(int)(front % array.length)];
                ++front;
            }
            front = 0;
            back = array.length - 1;
            array = newArray;
        }
        array[(int)(back % array.length)] = n;
        ++back;
        return "ok";
    }

    public String pop(){
        if(front == back){
            return "error";
        }
        ++front;
        return array[(int)((front - 1) % array.length)] + "";
    }

    public String front(){
        if(front == back){
            return "error";
        }
        return array[(int)(front % array.length)] + "";
    }

    public String size(){
        return (back - front) + "";
    }

    public String clear(){
        array = new int[15];
        front = 0;
        back = 0;
        return "ok";
    }
}