package org.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static List<Integer> listOfElements;
    public static void main(String[] args) throws IOException {
        int countOfMarks;
        int count;
        Integer hub;
        Set<Integer> set = new HashSet<>();
        Path path = Paths.get("input.txt");
        Path path1 = Paths.get("output.txt");
        try(BufferedReader reader = Files.newBufferedReader(path);
            BufferedWriter writer = Files.newBufferedWriter(path1);) {
            count = Integer.parseInt(reader.readLine());
            listOfElements = new ArrayList<>(count);
            char ch;
            int code;
            StringBuilder str;
            for (int i = 0; i < count; i++) {
                str = new StringBuilder();
                while ((code = reader.read()) < 58 && code > 47){
                    ch = (char)code;
                    str.append(ch);
                }
                hub = Integer.parseInt(str.toString());
                if(set.add(hub)){
                    listOfElements.add(hub);
                }
            }

            reader.readLine();
            count = Integer.parseInt(reader.readLine());
            listOfElements.sort((a,b) -> {
                if(a > b){
                    return 1;
                } else if(a < b){
                    return -1;
                } else {
                    return 0;
                }
            });

            for (int i = 0; i < count; i++) {
                str = new StringBuilder();
                while ((code = reader.read()) > 47 && code < 58){
                    ch = (char)code;
                    str.append(ch);
                }
                countOfMarks = binarySearch(Integer.parseInt(str.toString()));
                writer.write(countOfMarks + "");
                writer.newLine();
            }
        }



        }

        public static int binarySearch(int hub){
        if(hub < listOfElements.get(0)){
            return 0;
        } else if(hub > listOfElements.get(listOfElements.size() - 1)){
            return listOfElements.size();
        }
            int firstFlag = 0;
            int lastFlag = listOfElements.size() - 1;
            int middle;

            while(firstFlag < lastFlag){
                middle = (firstFlag + lastFlag) / 2;
                if(listOfElements.get(middle) == hub){
                    return middle;
                } else if (listOfElements.get(middle) > hub) {
                    lastFlag = middle;
                } else{
                    firstFlag = middle + 1;
                }
            }
            return firstFlag;
        }
}
