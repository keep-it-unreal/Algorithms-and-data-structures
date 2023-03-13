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
            int valueOfDigits = Integer.parseInt(reader.readLine());
            int[] array = new int[valueOfDigits];
            int code;
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < valueOfDigits; i++) {
                while ((code = reader.read()) > 47 && code < 58 || code == 45){
                    str.append((char) code);
                }
                array[i] = Integer.parseInt(str.toString());
                str = new StringBuilder();
            }
            SortedHeap sortedHeap = new SortedHeap(array);
            sortedHeap.sort();
            sortedHeap.lastFlag = valueOfDigits + 1;
            int[] finalArray = new int[valueOfDigits];
            for (int i = 0; i < valueOfDigits; i++) {
                finalArray[valueOfDigits - 1 - i] = sortedHeap.extract();
            }
            for (int e: finalArray) {
                writer.write(e + " ");
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

class SortedHeap{

    int[] array;
    int lastFlag;

    public SortedHeap(int[] array){
        this.array = array;
        lastFlag = array.length / 2 + 1;
    }

    public void sort(){
        if(array.length == 2){
            if(array[0] < array[1]){
                int hub = array[0];
                array[0] = array[1];
                array[1] = hub;
            }
            return;
        }
        int firstChild;
        int secondChild;
        int x = 2;
        int head = array.length / (2 * x) + 1;

        if(head == 1){
            firstChild = head * 2;
            secondChild = head * 2 + 1;
            changing(head, firstChild, secondChild);
            return;
        }
        while (head != 1) {
            head = array.length / (2 * x) + 1;
            while (head != lastFlag) {
                firstChild = head * 2;
                secondChild = head * 2 + 1;
                changing(head, firstChild, secondChild);
                ++head;
            }
            lastFlag = array.length / (2 * x) + 1;
            ++x;
        }
    }

    public void changing(int head, int firstChild, int secondChild){
        int hub;
        while (secondChild <= array.length && (array[head - 1] < array[firstChild - 1] || array[head - 1] < array[secondChild - 1])){
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

        if(firstChild == array.length){
            if(array[head - 1] < array[firstChild - 1]){
                hub = array[head - 1];
                array[head - 1] = array[firstChild - 1];
                array[firstChild - 1] = hub;
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
        while (secondChild <= lastFlag && (array[head - 1] < array[firstChild - 1] || array[head - 1] < array[secondChild - 1])){

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