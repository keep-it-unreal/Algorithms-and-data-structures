package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Main {

    public static void main(String[] args) {

        Path pathInput = Paths.get("input.txt");
        Path pathOutPut = Paths.get("output.txt");

        try (BufferedReader reader = Files.newBufferedReader(pathInput);
             BufferedWriter writer = Files.newBufferedWriter(pathOutPut)) {

            int curValue;
            int flag = 0;
            int countOfCity = Integer.parseInt(reader.readLine());
            int[] finalWorld = new int[countOfCity];
            int[][] myStack = new int[countOfCity][2];

            int code;
            StringBuilder curValueStr = new StringBuilder();
            while ((code = reader.read()) != 32 && code != 10 && code != -1 && code != 13){
                curValueStr.append((char) code);
            }
            curValue = Integer.parseInt(curValueStr.toString());
            curValueStr = new StringBuilder();
            int[] city = {curValue, 0};
            myStack[flag] = city;
            ++flag;

            for (int i = 1; i < countOfCity;) {
                while ((code = reader.read()) != 32 && code != 10 && code != -1 && code != 13){
                    curValueStr.append((char) code);
                }
                curValue = Integer.parseInt(curValueStr.toString());
                curValueStr = new StringBuilder();
                while (flag != 0 && curValue < myStack[flag - 1][0]) {
                    city = myStack[--flag];
                    finalWorld[city[1]] = i;
                }
                city = new int[]{curValue, i};
                myStack[flag] = city;
                ++flag;
                ++i;
            }

            for (int i = 0; i < flag; ++i) {
                city = myStack[i];
                finalWorld[city[1]] = -1;
            }

            for (int i = 0; i < countOfCity; i++) {
                writer.write(finalWorld[i] + " ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }