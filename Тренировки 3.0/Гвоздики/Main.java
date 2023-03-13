package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int countNails = Integer.parseInt(reader.readLine());
            String[] arrayStr = reader.readLine().split(" ");

            List<Integer> list = Arrays.stream(arrayStr).map(Integer::parseInt).sorted(Integer::compareTo).collect(Collectors.toList());

            System.out.println(findResult(list, countNails));
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static int findResult(List<Integer> list, int countNails){
        int result = list.get(1) - list.get(0);

        if(countNails == 2) {
            return result;
        } else if (countNails == 3) {
            return list.get(2) - list.get(0);
        }

        int[] array1 = new int[list.size()];
        array1[0] = result;
        int[] array2 = new int[list.size()];
        array2[0] = result;
        int curValue, prevValue, curResult;
        array1[1] = array1[0] + list.get(2) - list.get(1);
        array2[1] = array1[0];

        for (int i = 3; i < list.size(); i++) {
            curValue = list.get(i);
            prevValue = list.get(i - 1);
            curResult = curValue - prevValue;
            if(array1[i - 2] + curResult < array2[i - 2] + curResult){
                array1[i - 1] = array1[i - 2] + curResult;
            } else {
                array1[i - 1] = array2[i - 2] + curResult;
            }
            array2[i - 1] = array1[i - 2];
        }
        return array1[list.size() - 2];
    }
}