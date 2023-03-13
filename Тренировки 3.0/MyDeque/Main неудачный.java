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

    int[][] arrayOfArrays;
    int firstFlag;
    int lastFlag;
    int[] arrayOfFront;
    int[] arrayOfBack;
    long front;
    long back;

    public MyDeque(){
        arrayOfArrays = new int[1000][1000];
        firstFlag = 0;
        lastFlag = arrayOfArrays.length - 1;
        arrayOfFront = new int[1000];
        arrayOfBack = new int[1000];
        arrayOfArrays[firstFlag] = arrayOfFront;
        arrayOfArrays[lastFlag] = arrayOfBack;
        firstFlag++;
        lastFlag++;
        front = 0;
        back = 0;
    }

    public String pushFront(int n){
        arrayOfFront[(int)Math.abs(front % 1000)] = n;
        if(Math.abs(front % 1000) == 999){
            int[] newArray = new int[1000];
            arrayOfArrays[firstFlag] = newArray;
            firstFlag++;
            arrayOfFront = newArray;
        }
        ++front;
        return "ok";
    }

    public String pushBack(int n){
        arrayOfBack[(int)Math.abs(back % 1000)] = n;
        if(Math.abs(back % 1000) == 999){
            int[] newArray = new int[1000];
            arrayOfArrays[lastFlag] = newArray;
            lastFlag--;
            arrayOfBack = newArray;
        }
        ++back;
        return "ok";
    }

    public String popFront(){
        String result;
        boolean bool = false;
        if(front < 0 && back > 0 && Math.abs(front) == back){
            return "error";
        } else if(front > 0 && back < 0 && Math.abs(back) == front){
            return "error";
        } else if(front == 0 && back == 0){
            return "error";
        } else if (front == 0 && arrayOfBack != arrayOfFront) {
            arrayOfFront = arrayOfBack;
            lastFlag = arrayOfArrays.length - 1;
            arrayOfArrays[lastFlag] = null;
            result = arrayOfBack[0] + "";
            bool = true;
        } else {
            result = arrayOfBack[(int) Math.abs(--front % 1000)] + "";
        }

        if(front == 0 && bool){
            arrayOfArrays[--firstFlag] = null;
            arrayOfFront = arrayOfArrays[firstFlag - 1];
        }
        return result;
    }

    public String popBack(){
        String result;
        boolean bool = false;
        if(front < 0 && back > 0 && Math.abs(front) == back){
            return "error";
        } else if(front > 0 && back < 0 && Math.abs(back) == front){
            return "error";
        } else if(front == 0 && back == 0){
            return "error";
        } else if (back == 0 && arrayOfBack != arrayOfFront) {
            arrayOfBack = arrayOfFront;
            lastFlag = arrayOfArrays.length - 1;
            arrayOfArrays[lastFlag] = null;
            result = arrayOfBack[0] + "";
            bool = true;
        } else {
            result = arrayOfBack[(int) Math.abs(--back % 1000)] + "";
        }

        if(back == 0 && bool){
            arrayOfArrays[++lastFlag] = null;
            arrayOfFront = arrayOfArrays[lastFlag - 1];
        }
        return result;
    }

    public String front(){
        if(front < 0 && back > 0 && Math.abs(front) == back){
            return "error";
        } else if(front > 0 && back < 0 && Math.abs(back) == front){
            return "error";
        } else if(front == 0 && back == 0){
            return "error";
        } else if (front == 0) {
            return arrayOfBack[0] + "";
        } else {
            return arrayOfFront[(int) ((front - 1) % 1000)] + "";
        }
    }


    public String back(){
        if(front < 0 && back > 0 && Math.abs(front) == back){
            return "error";
        } else if(front > 0 && back < 0 && Math.abs(back) == front){
            return "error";
        } else if(front == 0 && back == 0){
            return "error";
        } else if (back == 0) {
            return arrayOfFront[0] + "";
        } else {
            return arrayOfBack[(int) ((back - 1) % 1000)] + "";
        }
    }

    public String size(){
        return (front + back) + "";
    }

    public String clear(){
        arrayOfArrays = new int[1000][1000];
        firstFlag = 0;
        lastFlag = arrayOfArrays.length - 1;
        arrayOfFront = new int[1000];
        arrayOfBack = new int[1000];
        arrayOfArrays[firstFlag] = arrayOfFront;
        arrayOfArrays[lastFlag] = arrayOfBack;
        firstFlag++;
        lastFlag++;
        front = 0;
        back = 0;
        return "ok";
    }
}