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
            MyDeque myDeque = new MyDeque();
            String[] arrayFromPush = new String[2];

            while (true) {
                str = reader.readLine();
                if (str.equals("pop_front")) {
                    writer.write(myDeque.popFront());
                } else if (str.equals("pop_back")) {
                    writer.write(myDeque.popBack());
                } else if (str.equals("front")) {
                    writer.write(myDeque.front());
                } else if (str.equals("back")) {
                    writer.write(myDeque.back());
                } else if (str.equals("size")) {
                    writer.write(myDeque.size());
                } else if (str.equals("clear")) {
                    writer.write(myDeque.clear());
                } else if (str.equals("exit")) {
                    break;
                } else {
                    arrayFromPush = str.split(" ");
                    if(arrayFromPush[0].equals("push_front")) {
                        writer.write(myDeque.pushFront(Integer.parseInt(arrayFromPush[1])));
                    } else {
                        writer.write(myDeque.pushBack(Integer.parseInt(arrayFromPush[1])));
                    }
                }
                writer.newLine();
            }
            writer.write("bye");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

class MyDeque{

    int[] array;
    int front;
    int back;

    public MyDeque(){
        array = new int[15];
        front = 7;
        back = 8;
    }

    public String pushFront(int n){
        array[front] = n;
        if(front == 0){
            int[] newArray = new int[array.length * 2];
            int firstFlag = newArray.length / 4;
            front = firstFlag;
            for (int i = 0; i < back; i++) {
                newArray[firstFlag] = array[i];
                ++firstFlag;
            }
            array = newArray;
            back = front + back;
        }
        --front;
        return "ok";
    }

    public String pushBack(int n){
        array[back] = n;
        if(back == array.length - 1){
            int[] newArray = new int[array.length * 2];
            int firstFlag = newArray.length / 4;
            for (int i = 0; i < back - front; i++) {
                newArray[firstFlag] = array[front + i + 1];
                ++firstFlag;
            }
            array = newArray;
            front = newArray.length / 4 - 1;
            back = firstFlag - 1;
        }
        ++back;
        return "ok";
    }

    public String popFront(){
        if(front == back - 1){
            return "error";
        }
        return array[++front] + "";
    }

    public String popBack(){
        if(front == back - 1){
            return "error";
        }
        return array[--back] + "";
    }

    public String front(){
        if(front == back - 1){
            return "error";
        }
        return array[front + 1] + "";
    }


    public String back(){
        if(front == back - 1){
            return "error";
        }
        return array[back - 1] + "";
    }

    public String size(){
        return (back - front - 1) + "";
    }

    public String clear(){
        array = new int[15];
        front = 7;
        back = 8;
        return "ok";
    }
}