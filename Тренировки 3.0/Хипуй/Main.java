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
            int valueOfRequest = Integer.parseInt(reader.readLine());
            MyHeap myHeap = new MyHeap();
            String[] array;
            for (int i = 0; i < valueOfRequest; i++) {
                array = reader.readLine().split(" ");
                if(array.length == 1){
                    writer.write(myHeap.extract() + "");
                    writer.newLine();
                } else {
                    myHeap.insert(Integer.parseInt(array[1]));
                }
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

class MyHeap{

    int[] array;
    int lastFlag;
    int curFlag;
    int prevFlag;

    public MyHeap(){
        array = new int[15];
        lastFlag = 1;
    }

    public void insert(int n){
        if(lastFlag - 1 == array.length){
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; ++i) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[lastFlag - 1] = n;
        curFlag = lastFlag;
        if(lastFlag % 2 == 0){
            prevFlag = lastFlag / 2;
            changing();
        } else {
            prevFlag = (lastFlag - 1) / 2;
            changing();
        }
        ++lastFlag;
    }

    public void changing() {
        int hub;
        while (curFlag > 1 && array[curFlag - 1] > array[prevFlag - 1]) {
            hub = array[curFlag - 1];
            array[curFlag - 1] = array[prevFlag - 1];
            array[prevFlag - 1] = hub;
            curFlag = prevFlag;
            if (curFlag % 2 == 0) {
                prevFlag = curFlag / 2;
            } else {
                prevFlag = (curFlag - 1) / 2;
            }
        }
    }

    public int extract(){
        int result = array[0];
        if(lastFlag == 3){
            array[0] = array[1];
            --lastFlag;
            return result;
        }
        array[0] = array[lastFlag - 2];
        int head = 1;
        int firstChild = 2;
        int secondChild = 3;
        int hub;
        while (secondChild <= lastFlag - 1 && (array[head - 1] < array[firstChild - 1] || array[head - 1] < array[secondChild - 1])){

            if(array[firstChild - 1] > array[secondChild - 1]){
                hub = array[head - 1];
                array[head - 1] = array[firstChild - 1];
                array[firstChild - 1] = hub;
                head = firstChild;
            } else {
                hub = array[head - 1];
                array[head - 1] = array[secondChild - 1];
                array[secondChild - 1] = hub;
                head = secondChild;
            }
            firstChild = head * 2;
            secondChild = head * 2 + 1;
        }

        --lastFlag;
        return result;
    }
}